package com.rp.sec01;

import com.rp.courseutil.Util;
import com.rp.sec01.assignment.FileService;
import java.io.IOException;

public class Lec09AssignmentDemo {

    public static void main(String[] args) throws IOException {

        System.out.println("Press Enter to exit");

        FileService.write("file03.txt", "This is file3")
                .subscribe(Util.onNext(), Util.onError(), Util.onComplete());

        FileService.read("file03.txt")
                .subscribe(Util.onNext(), Util.onError(), Util.onComplete());

        FileService.delete("file03.txt")
                .subscribe(Util.onNext(), Util.onError(), Util.onComplete());

        System.in.read();

    }

}
