function() {	 
	var importPackageTag = "Import-Package";
	var versionRangeBeginValue = "version=\"[";
	var versionBeginValue = "version=\"";
	var versionEndValue = ")\"";
	var exclusionValue = "!";
	var resolutionValue = "resolution";

	var isFoundVersion = false;

	var importPackageValues = utils.getBundlePluginInstructionValues(importPackageTag);

   // println("Before loop");
	var iterator = importPackageValues.iterator();

    while (iterator.hasNext()) {
		var importPackageValue = iterator.next();

		//println("importPackageValue : ");
		//println(importPackageValue);

		var lineValues = importPackageValue.split("\\n");

		for (var i = 0; i < lineValues.length; ++i) {
			//println("lineValues[" + i + "] : ");
            //println(lineValues[i]);
			var packageInfo = extractPackageInfo(lineValues[i]);

			var packageName = String(packageInfo[0]);
			var packageVersion = String(packageInfo[1]);
			//println("packageInfo : ");
            //println(packageName);
            //println(packageVersion);

            var isVersionLatest = false;

			if (-1 != packageVersion.search(",")) {
				var splitValue = packageVersion.split(",");

				isVersionLatest = validateVersionRange(packageName, splitValue);
			}
			else {
				isVersionLatest = validateVersion(packageName, packageVersion);
			}

		    if (!isVersionLatest) {
                utils.logInfo("=========================================");
                utils.logInfo("Imported package version is not the latest");
                utils.logInfo("=========================================");
                utils.logInfo("Package name : " + packageName);
                utils.logInfo("Version Imported : " + packageVersion + ", Available latest version : " +
                                        "4.0.0");
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
			var packageName = splitPackage[i];				
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
		
		//return utils.isPackageImportLatest(packageName, packageVersion);
		return true;
	}
}




