function() {
	var importPackageTag = "Import-Package";
	var versionRangeBeginValue = "version=\"[";
	var versionBeginValue = "version=\"";
	var versionEndValue = ")\"";
	var exclusionValue = "!";
	var resolutionValue = "resolution";

	var isFoundVersion = false;

	var importPackageValues = utils.getBundlePluginInstructionValues(importPackageTag);

	var iterator = importPackageValues.iterator();

    	while (iterator.hasNext()) {
            var importPackageValue = iterator.next();

            var lineValues = importPackageValue.split("\\n");

            for (var i = 0; i < lineValues.length; ++i) {
                var packageInfo = extractPackageInfo(lineValues[i]);
                var packageName = String(packageInfo[0]);
                var packageVersion = String(packageInfo[1]);

                var isVersionExported = false;

                if (-1 != packageVersion.search(",")) {
                    var splitValue = packageVersion.split(",");

                    isVersionExported = validateVersionRange(packageName, splitValue);
                }
                else {
                    isVersionExported = validateVersion(packageName, packageVersion);
                }

                if (!isVersionExported) {
                    utils.logInfo("=========================================");
                    utils.logInfo("Imported package version is not Exported");
                    utils.logInfo("=========================================");
                    utils.logInfo("Package name : " + packageName + ", Imported version : " + packageVersion);
                    utils.logInfo("Exported versions :");

                    var exportedVersions = utils.getExportedPackageVersions(packageName);
                    var versionsIterator = exportedVersions.iterator();

                    while (versionsIterator.hasNext()) {
                        utils.logInfo(versionsIterator.next());
                    }
                }
            }
    	}

	return true;


	function validateVersionRange(packageName, versionArray) {
		for (var i = 0; i < versionArray.length; ++i) {
		    if (validateVersion(packageName, versionArray[i])) {
			    return true;
		    }
		}

	    return false;
	}

	function extractPackageInfo(lineValue) {
		var splitPackage = lineValue.split(";");

		var packageInfo = new Array();

		for (var i = 0; i < splitPackage.length; ++i) {
			var packageName = splitPackage[i].trim();
			var packageVersion = splitPackage[++i];

			var version = "";

			if (!packageVersion.contains(versionRangeBeginValue)) {
				version = packageVersion.substr(versionBeginValue.length, packageVersion.search(/\"(,|$)/) - versionBeginValue.length);
			}
			else {
				version = packageVersion.substr(versionRangeBeginValue.length, packageVersion.indexOf(versionEndValue) - versionRangeBeginValue.length);
			}

			packageInfo[0] = packageName;
			packageInfo[1] = version;
		}

		return packageInfo;
	}


	function validateVersion(packageName, packageVersion) {
		if (-1 != String(packageVersion).search(exclusionValue)) {
		    return true;
		}

		if (-1 != String(packageVersion).search(resolutionValue)) {
		    return true; // Since the resolution option is set no need to check version
		}

		return utils.isPackageVersionExported(packageName, packageVersion);
	}
}