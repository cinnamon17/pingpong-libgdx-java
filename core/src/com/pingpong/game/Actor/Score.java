package com.pingpong.game.Actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.pingpong.game.GameHandler;

/**
 * Score
 */
public class Score extends Table {
	private GameHandler game;
	private Skin skin;
	private Label playerScore;
	private Label enemyScore;

	public Score(final GameHandler game) {
		super();
		this.game = game;
		this.setPosition(0, 90);
		this.setVisible(false);
		this.setFillParent(true);
		this.skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
		this.playerScore = new Label("Player: " + this.game.getScorePlayer(), this.skin);
		this.enemyScore = new Label("Enemy: " + this.game.getScoreEnemy(), this.skin);
		this.add(playerScore).fillX().uniformX();
		this.row().pad(10, 0, 10, 0);
		this.add(enemyScore).fillX().uniformX();
		this.row();
	}

	public void update() {

		this.playerScore.setText("Player: " + this.game.getScorePlayer());
		this.enemyScore.setText("Enemy: " + this.game.getScoreEnemy());
	}
}
