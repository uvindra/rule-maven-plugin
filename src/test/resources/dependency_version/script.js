function() {
    if (false == utils.isChildPOM()) {  // Ignore parent projects
        return true;
    }

    var dependencyTag = "dependency";
    var versionTag = "version";
    var groupIdTag = "groupId";
    var carbonGroup = "org.wso2.carbon";

    var nodeLists = utils.getChildrenOfParent(dependencyTag);

    var iterator = nodeLists.iterator();

    while (iterator.hasNext()) {
        var nodeList = iterator.next();

        var isExclude = false;
        var isVersionPresent = false;

        for (var i = 0; i < nodeList.getLength(); ++i) {
            var node = nodeList.item(i);

            // Carbon group dependencies should be excluded from this check
            if ((groupIdTag === node.getNodeName()) && (carbonGroup === node.getTextContent())) {
                isExclude = true;
            }

            if (versionTag === node.getNodeName()) {
                isVersionPresent = true;
            }
        }

        if (false == isExclude && true == isVersionPresent) {
            utils.setErrorString("Child pom contains version tag in dependency section");
            return false;
        }
    }

    return true;
}