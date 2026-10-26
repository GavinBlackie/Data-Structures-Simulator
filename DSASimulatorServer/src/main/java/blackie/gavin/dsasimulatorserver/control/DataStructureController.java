package blackie.gavin.dsasimulatorserver.control;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import blackie.gavin.dsasimulatorserver.domain.Stack;

@RestController
public class DataStructureController {

    @GetMapping("/test")
    public int test() {
        Stack stack = new Stack();

        return stack.test();
    }
}
