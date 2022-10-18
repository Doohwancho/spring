package com.cho.rest.robot;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RobotService {
    private final RobotRepository robotRepository;

    public Robot createRobot(final Robot robot) {
        return robotRepository.save(robot);
    }

    public Robot queryRobot(final Integer id) {
        return robotRepository.findById(id).get();
    }

    public Robot updateRobot(Robot robot) {
        return robot;
    }
}
