package mx.metaphorce.entity;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import mx.metaphorce.controllers.UserController;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<User, EntityModel<User>> {

	@Override
	public EntityModel<User> toModel(User user) {
		return EntityModel.of(user, linkTo(methodOn(UserController.class).getUserById(user.getId())).withSelfRel(),
				linkTo(methodOn(UserController.class).fetchUserPage(0, 30)).withRel("users"));
	}
}
