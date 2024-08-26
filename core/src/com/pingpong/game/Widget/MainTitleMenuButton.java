package com.pingpong.game.Widget;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.net.ServerSocket;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.ServerSocketHints;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.pingpong.game.GameHandler;

/**
 * Menu buttons items
 */
public class MainTitleMenuButton {

	private Table table;
	private TextButton newGame;
	private TextButton multiplayer;
	private TextButton connect;
	private TextButton exit;
	private Skin skin;
	private GameHandler game;

	public MainTitleMenuButton(final GameHandler game) {

		this.game = game;

		this.table = new Table();
		this.table.setFillParent(true);
		this.game.getMainTitleStage().addActor(table);
		this.skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

		this.newGame = new TextButton("Start/Resume", this.skin);
		this.multiplayer = new TextButton("Multiplayer", this.skin);
		this.connect = new TextButton("Connect", this.skin);
		this.exit = new TextButton("Exit", this.skin);
		this.table.add(newGame).fillX().uniformX();
		this.table.row().pad(10, 0, 10, 0);
		this.table.add(multiplayer).fillX().uniformX();
		this.table.row();
		this.table.add(connect).fillX().uniformX();
		this.table.row().pad(10, 0, 10, 0);
		this.table.add(exit).fillX().uniformX();

	}

	public void create() {

		this.newGame.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log("MainTitleScreen.java", "new game button pressed");
				game.setScreen(game.getGameScreen());
			}

		});

		this.multiplayer.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log("MainTitleScreen.java", "Starting Server Socket");
				ServerSocket server = Gdx.net.newServerSocket(Net.Protocol.TCP, "localhost",
						8080,
						new ServerSocketHints());

				Gdx.app.log("MainTitleScreen.java", "Waiting for connection");
				Socket socket = server.accept(new SocketHints());
				Gdx.app.log("MainTitleScreen.java", "client connected");
				OutputStream outputStream = socket.getOutputStream();
				InputStream inputStream = socket.getInputStream();

				try {
					outputStream.write("sent this to the server".getBytes());
					Gdx.app.log("[Server]: MainTitleScreen", "data sent");
				} catch (IOException e) {
					Gdx.app.log("[Server]: MainTitleScreen", "error sending data", e);
				}

			}

		});

		this.connect.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log("MainTitleScreen.java", "connecting to server");
				Socket socket = Gdx.net.newClientSocket(Net.Protocol.TCP, "localhost", 8080,
						new SocketHints());
				Gdx.app.log("MainTitleScreen.java", "connected successfully");
				OutputStream outputStream = socket.getOutputStream();
				InputStream inputStream = socket.getInputStream();

				byte[] b = new byte[1024];

				try {
					inputStream.read(b);
					Gdx.app.log("[Client]: MainTitleScreen", "data received");
				} catch (IOException e) {
					Gdx.app.log("[Client]: MainTitleScreen", "error reading data", e);
				}

				String readedData = new String(b);
				Gdx.app.log("[Client]", readedData);
			}

		});
		this.exit.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log("MainTitleScreen.java", "exit button pressed");
				Gdx.app.exit();
			}
		});
	}
}
