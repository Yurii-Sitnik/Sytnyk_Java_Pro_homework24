public class TestClass {
    @BeforeSuite
    public void before() {
        System.out.println("Before suite method!!!");
    }

    @AfterSuite
    public void after() {
        System.out.println("After suite method!!!");
    }

    @Test(value = 1)
    public void test1() {
        System.out.println("Test 1");
    }

    @Test(value = 2)
    public void test2() {
        System.out.println("Test 2");
    }

    @Test(value = 3)
    public void test3() {
        System.out.println("Test 3");
    }
}

