/**
 * This file was auto-generated by mofcomp -j version 1.0.0 on Wed Jan 12 
 * 09:21:06 CET 2011. 
 */

package net.i2cat.mantychore.model;

import java.io.*;

/**
 * This Class contains accessor and mutator methods for all properties defined 
 * in the CIM class View as well as methods comparable to the invokeMethods 
 * defined for this class. This Class implements the ViewBean Interface. The 
 * CIM class View is described as follows: 
 * 
 * View is an abstract class that provides a common superclass for classes 
 * providing de-normalized, aggregate representations of managed resources. 
 * The definition of each sub-class will include properties propagated from 
 * the the graph of classes that are used to model the resource in the 
 * normalized view. The classes may be resource classes or associations. The 
 * definition of how a value is propagated (i.e. source class and value 
 * transformations) is required to be specified. Sub-classes may be 
 * explicitly constrained to be read only. If a sub-class is not constrained 
 * as read only, the designers are strongly encouraged to carefully consider 
 * the data synchronization and consistencies issues that may result. The 
 * ElementView association may be used to find the instances that form the 
 * normalized view of the managed resource. 
 */
public class View extends ManagedElement implements Serializable {

    /**
     * This constructor creates a ViewBeanImpl Class which implements the 
     * ViewBean Interface, and encapsulates the CIM class View in a Java 
     * Bean. The CIM class View is described as follows: 
     * 
     * View is an abstract class that provides a common superclass for classes 
     * providing de-normalized, aggregate representations of managed 
     * resources. The definition of each sub-class will include properties 
     * propagated from the the graph of classes that are used to model the 
     * resource in the normalized view. The classes may be resource classes 
     * or associations. The definition of how a value is propagated (i.e. 
     * source class and value transformations) is required to be specified. 
     * Sub-classes may be explicitly constrained to be read only. If a 
     * sub-class is not constrained as read only, the designers are strongly 
     * encouraged to carefully consider the data synchronization and 
     * consistencies issues that may result. The ElementView association may 
     * be used to find the instances that form the normalized view of the 
     * managed resource. 
     */
    protected View(){};
} // Class View