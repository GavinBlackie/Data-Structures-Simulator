package blackie.gavin.dsasimulatorserver.control;

import blackie.gavin.dsasimulatorserver.domain.Linear.CircularQueue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import blackie.gavin.dsasimulatorserver.domain.Linear.Stack;

@RestController
public class QueueController {

    CircularQueue queue;

    public QueueController() {
        this.queue = new CircularQueue();
    }

    @PostMapping("/enqueue")
    public void enqueueElement(int element) {
        queue.enqueue(element);
    }

    @GetMapping("/dequeue")
    public Integer dequeueElement() {
        return queue.dequeue();
    }

    @GetMapping("/test")
    public int test() {
        Stack stack = new Stack();
        stack.push(5);
        return stack.pop();
    }
}
