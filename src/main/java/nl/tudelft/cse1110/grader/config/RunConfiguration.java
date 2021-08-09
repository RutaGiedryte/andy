package nl.tudelft.cse1110.grader.config;

import kotlin.DeepRecursiveFunction;
import nl.tudelft.cse1110.grader.util.ClassUtils;

import java.util.List;

public class RunConfiguration {

    private DirectoryConfiguration dirCfg;

    protected static final List<String> OLD_DEFAULTS = List.of("CONDITIONALS_BOUNDARY", "INCREMENTS", "INVERT_NEGS", "MATH",
            "NEGATE_CONDITIONALS", "RETURN_VALS", "VOID_METHOD_CALLS");

    protected static final List<String> DEFAULTS = List.of("CONDITIONALS_BOUNDARY", "INCREMENTS", "INVERT_NEGS", "MATH",
            "NEGATE_CONDITIONALS", "VOID_METHOD_CALLS", "EMPTY_RETURNS", "FALSE_RETURNS", "TRUE_RETURNS",
            "NULL_RETURNS", "PRIMITIVE_RETURNS");

    protected static final List<String> STRONGER = List.of("CONDITIONALS_BOUNDARY", "INCREMENTS", "INVERT_NEGS", "MATH",
            "NEGATE_CONDITIONALS", "VOID_METHOD_CALLS", "EMPTY_RETURNS", "FALSE_RETURNS", "TRUE_RETURNS",
            "NULL_RETURNS", "PRIMITIVE_RETURNS", "REMOVE_CONDITIONALS_EQ_ELSE", "EXPERIMENTAL_SWITCH");

    protected static final List<String> ALL = List.of("CONDITIONALS_BOUNDARY", "INCREMENTS", "INVERT_NEGS", "MATH",
            "NEGATE_CONDITIONALS", "VOID_METHOD_CALLS", "EMPTY_RETURNS", "FALSE_RETURNS", "TRUE_RETURNS",
            "NULL_RETURNS", "PRIMITIVE_RETURNS", "REMOVE_CONDITIONALS", "EXPERIMENTAL_SWITCH",
            "INLINE_CONSTS", "NON_VOID_METHOD_CALLS", "REMOVE_CONDITIONALS", "REMOVE_INCREMENTS", "EXPERIMENTAL_ARGUMENT_PROPAGATION",
            "EXPERIMENTAL_BIG_INTEGER", "EXPERIMENTAL_NAKED_RECEIVER", "EXPERIMENTAL_MEMBER_VARIABLE", "ABS",
            "AOR", "AOD", "CRCR", "OBBN", "ROR", "UOI");

    public RunConfiguration(DirectoryConfiguration dirCfg) {
        this.dirCfg = dirCfg;
    }

    public List<String> classesUnderTest() {
        return ClassUtils.allClassesButTestingAndConfigOnes(this.dirCfg.getNewClassNames());
    }

    public List<String> listOfMutants() {
        return DEFAULTS;
    }

}
