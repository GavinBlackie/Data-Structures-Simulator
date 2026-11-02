package blackie.gavin.dsasimulatorserver.control;

import blackie.gavin.dsasimulatorserver.domain.Linear.CircularQueue;
import blackie.gavin.dsasimulatorserver.service.StructureService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import blackie.gavin.dsasimulatorserver.domain.Linear.Stack;

@RestController
public class QueueController {

    private final StructureService structService;

    public QueueController(StructureService structService) {
        this.structService = structService;
    }

    @PostMapping("/enqueue")
    public void enqueueElement(int element) {
        structService.getQueue().enqueue(element);
    }

    @GetMapping("/dequeue")
    public Integer dequeueElement() {
        return structService.getQueue().dequeue();
    }

    @GetMapping("/test")
    public int test() {
        Stack stack = new Stack();
        stack.push(5);
        return stack.pop();
    }
}
