package com.pingpong.game.Actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.pingpong.game.GameHandler;

/**
 * Score
 */
public class Score {
	private GameHandler game;
	private Skin skin;
	private Table table;
	private Label playerScore;
	private Label enemyScore;

	public Score(final GameHandler game) {
		this.game = game;
		this.table = new Table();
		this.table.setPosition(0, 90);
		this.table.setFillParent(true);
		this.game.getStage().addActor(table);
		this.skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
		this.playerScore = new Label("Player: " + this.game.getScorePlayer(), this.skin);
		this.enemyScore = new Label("Enemy: " + this.game.getScoreEnemy(), this.skin);
		this.table.add(playerScore).fillX().uniformX();
		this.table.row().pad(10, 0, 10, 0);
		this.table.add(enemyScore).fillX().uniformX();
		this.table.row();
	}

	public void show() {
		this.table.setVisible(true);
	}

	public void hide() {
		this.table.setVisible(false);
	}
}
