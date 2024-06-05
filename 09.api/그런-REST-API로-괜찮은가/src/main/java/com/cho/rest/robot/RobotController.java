package com.cho.rest.robot;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/api/robots", produces = MediaTypes.HAL_JSON_VALUE)
public class RobotController {
    private RobotService robotService;

    public RobotController(RobotService robotService) {
        this.robotService = robotService;
    }

    @PostMapping
    public ResponseEntity createRobot(@RequestBody Robot robot) {
        Robot newRobot = robotService.createRobot(robot);
        WebMvcLinkBuilder builder = linkTo(RobotController.class);
        URI uri = builder.toUri();

        // HAL
        EntityModel<Robot> entityModel = EntityModel.of(newRobot);
        entityModel.add(linkTo(RobotController.class).withSelfRel());
        entityModel.add(linkTo(RobotController.class).slash(newRobot.getId()).withRel("detail"));

        return ResponseEntity.created(uri).body(entityModel);
    }

    @PutMapping
    public ResponseEntity updateRobot(@RequestBody Robot robot) {
        Robot updateRobot = robotService.updateRobot(robot);

        // HAL
        EntityModel<Robot> entityModel = EntityModel.of(updateRobot);
        entityModel.add(linkTo(RobotController.class).withSelfRel());
        entityModel.add(linkTo(RobotController.class).slash(updateRobot.getId()).withRel("detail"));

        return ResponseEntity.ok(entityModel);
    }

    @GetMapping(value = "/{robotId}")
    public ResponseEntity robotDetail(@PathVariable(value = "robotId") final Integer robotId) {
        Robot robot = robotService.queryRobot(robotId);

        // HAL
        EntityModel<Robot> entityModel = EntityModel.of(robot);
        entityModel.add(linkTo(RobotController.class).slash(robotId).withSelfRel());
        entityModel.add(linkTo(RobotController.class).withRel("create"));
        return ResponseEntity.ok(entityModel);
    }
}