package game.object.notused;

import org.jsfml.graphics.ConstTexture;
import org.jsfml.graphics.Texture;

import game.behavior.AnimationBehaviour;
import game.behavior.AnimationStateBehaviour;
import game.world.object.GameObject;

public class Player extends GameObject {
	public Player(ConstTexture texture) {
		addTexture(new Texture(texture));
		addBehaviour(new AnimationBehaviour(sprite, 0, 7, 7, 28), GameObject.Ability.MOVE_DOWN.ordinal());
		addBehaviour(new AnimationBehaviour(sprite, 7, 14, 7, 28), GameObject.Ability.MOVE_UP.ordinal());
		addBehaviour(new AnimationBehaviour(sprite, 14, 21, 7, 28), GameObject.Ability.MOVE_LEFT.ordinal());
		addBehaviour(new AnimationBehaviour(sprite, 21, 28, 7, 28), GameObject.Ability.MOVE_RIGHT.ordinal());
		addBehaviour(new AnimationStateBehaviour(anims, abilities));
	}
}
