package blackie.gavin.dsasimulatorserver.service;

import blackie.gavin.dsasimulatorserver.domain.Linear.CircularQueue;
import blackie.gavin.dsasimulatorserver.domain.Linear.Singly;
import blackie.gavin.dsasimulatorserver.domain.Linear.Stack;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
public class StructureService {
    private final Stack stack = new Stack();
    private final CircularQueue queue = new CircularQueue();
    private final Singly<Character> singlyCharList = new Singly<>();
}
