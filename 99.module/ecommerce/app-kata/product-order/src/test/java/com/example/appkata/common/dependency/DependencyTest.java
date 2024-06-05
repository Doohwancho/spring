package com.example.appkata.common.dependency;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;
import static com.tngtech.archunit.library.Architectures.*;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.*;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
@AnalyzeClasses(packages = "com.example.appkata", importOptions = ImportOption.DoNotIncludeTests.class)
class DependencyTest {
    private static final String APPLICATION_PACKAGE = "..application..";
    private static final String API_PACKAGE = "..api..";
    private static final String LOGIN_PACKAGE = "..login..";
    private static final String ACCOUNT_PACKAGE = "..account..";
    private static final String PRODUCT_PACKAGE = "..product..";
    private static final String COMMON_PACKAGE = "..common..";
    private static final String MODULE_PACKAGE = "..module..";
    private static final String ORDER_PACKAGE = "..order..";
    private static final String API_LAYER = "Api";
    private static final String APPLICATION_LAYER = "Application";

    //TODO - c-a-4: package간 의존성 테스트
    @ArchTest // application 패키지는 API 패키지만 엑세스 해야함. domain이나 infra에서 엑세스 하면 안됨.
    static final ArchRule application_should_only_be_accessed_by_apis = classes().that().resideInAPackage(APPLICATION_PACKAGE)
            .should().onlyBeAccessed().byAnyPackage(API_PACKAGE, APPLICATION_PACKAGE);

    @ArchTest // login 패키지는 account 패키지만 엑세스 해야함
    static final ArchRule login_should_only_be_accessed_by_accounts =
            classes().that().resideInAPackage(LOGIN_PACKAGE)
                    .should().onlyBeAccessed().byClassesThat().resideInAnyPackage(ACCOUNT_PACKAGE, LOGIN_PACKAGE);

    @ArchTest // module 패키지가 common 패키지를 의존하면 안됨
    static final ArchRule module_should_not_depend_on_common =
            noClasses().that().resideInAPackage(MODULE_PACKAGE)
                    .should().dependOnClassesThat().resideInAPackage(COMMON_PACKAGE);

    @ArchTest // common 패키지는 common 패키지만 의존함.
    static final ArchRule common_should_common_depend_on =
            classes().that().resideInAPackage(COMMON_PACKAGE)
                    .should().onlyBeAccessed().byClassesThat()
                    .resideInAnyPackage(COMMON_PACKAGE);

    @ArchTest // module 패키지는 모듈패키지만 의존한다.
    static final ArchRule module_should_module_depend_on =
            classes().that().resideInAPackage(MODULE_PACKAGE)
                    .should().onlyBeAccessed().byClassesThat()
                    .resideInAnyPackage(MODULE_PACKAGE);

    @ArchTest // product 패키지는 product, order 패키지만 의존한다.
    static final ArchRule product_should_product_depend_on =
            classes().that().resideInAPackage(PRODUCT_PACKAGE)
                    .should().onlyBeAccessed().byClassesThat()
                    .resideInAnyPackage(ORDER_PACKAGE, PRODUCT_PACKAGE);


    @ArchTest // application 패키지는 api 패키지에서만 엑세스 해야함
    static final ArchRule mvc_layered_test =
            layeredArchitecture()
                    .layer(API_LAYER).definedBy(API_PACKAGE)
                    .layer(APPLICATION_LAYER).definedBy(APPLICATION_PACKAGE)

                    .whereLayer(API_LAYER).mayNotBeAccessedByAnyLayer()
                    .whereLayer(APPLICATION_LAYER).mayOnlyBeAccessedByLayers(API_LAYER);


    //TODO - c-a-4-1: no cyclinic dependencies in 'module' packages
    @ArchTest
    static final ArchRule cycle_check =
            slices().matching("..module.(*)..")
                    .should().beFreeOfCycles();

}
