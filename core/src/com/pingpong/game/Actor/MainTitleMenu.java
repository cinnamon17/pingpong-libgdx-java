package com.pingpong.game.Actor;

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
public class MainTitleMenu extends Table {

	private TextButton newGame;
	private TextButton multiplayer;
	private TextButton connect;
	private TextButton exit;
	private Skin skin;
	private GameHandler game;

	public MainTitleMenu(final GameHandler game) {

		this.game = game;

		this.setFillParent(true);
		this.skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

		this.newGame = new TextButton("Play", this.skin);
		this.multiplayer = new TextButton("Multiplayer", this.skin);
		this.connect = new TextButton("Connect", this.skin);
		this.exit = new TextButton("Exit", this.skin);
		this.add(newGame).fillX().uniformX();
		this.row().pad(10, 0, 10, 0);
		this.add(multiplayer).fillX().uniformX();
		this.row();
		this.add(connect).fillX().uniformX();
		this.row().pad(10, 0, 10, 0);
		this.add(exit).fillX().uniformX();

	}

	public void create() {

		this.setVisible(true);
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

				ServerSocketHints serverSocketHints = new ServerSocketHints();
				serverSocketHints.acceptTimeout = 0;
				ServerSocket server = Gdx.net.newServerSocket(Net.Protocol.TCP, "localhost",
						8080,
						serverSocketHints);

				Thread serverListener = new Thread() {
					@Override
					public void run() {
						Gdx.app.log("MainTitleScreen.java", "Waiting for connection");
						Socket socket = server.accept(new SocketHints());
						Gdx.app.log("MainTitleScreen.java", "client connected");
						game.setServerDataInputStream(socket.getInputStream());
						game.setServerDataOutputStream(socket.getOutputStream());
						game.setIsServer(true);
						game.setScreen(game.getGameScreen());
					}
				};
				serverListener.start();
				game.setScreen(game.getServerScreen());
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

				game.setClientDataInputStream(inputStream);
				game.setClientDataOutputStream(outputStream);
				game.setScreen(game.getGameScreen());
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

	public void removeEventListeners() {
		this.setVisible(false);
	}
}
