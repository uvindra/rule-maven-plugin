package com.wso2.build.interfaces;

import com.wso2.build.beans.Rule;
import java.util.List;

/**
 * Created by uvindra on 2/8/14.
 */
public interface RuleRegistry {
    List<Rule> getRules();
}
