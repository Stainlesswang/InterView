package allen;

import allen.interview.designPatterns.singleton.SomeThing;

import java.io.File;

public class YoungTest {

        public static void main(String[] args) {
            System.out.println("创建一个实例");
            SomeThing someThing=SomeThing.getInstance();

        }
}