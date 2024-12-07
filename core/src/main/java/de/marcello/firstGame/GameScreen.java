package de.marcello.firstGame;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class GameScreen extends ScreenAdapter {

    ShapeRenderer shapeRenderer;
    Vector2 paddlePosition;
    Vector2 ballPosition;
    Vector2 ballVelocity;
    float ballRadius = 15.0f;
    float maxVelocity = 550.0f;
    float paddleWidth = 100.00f;
    float paddleHeight = 32.0f;
    Random random;
    Vector2 paddlePositionHigh;
    float paddleWidthHigh = 100.00f;
    float paddleHeightHigh = 32.0f;
	public GameScreen() {

        shapeRenderer = new ShapeRenderer();
        paddlePosition = new Vector2(Gdx.graphics.getWidth() / 2 -  paddleWidth / 2, 32.0f);
        ballPosition = new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() /2);
        random = new Random();
        ballVelocity = new Vector2(random.nextFloat() * 200 - 100, 300.0f);
        paddlePositionHigh = new Vector2(Gdx.graphics.getWidth() / 2 -  paddleWidth /2, Gdx.graphics.getHeight() - 65.0f);
	}
	
	@Override
	public void render(float delta) {
		if(Gdx.input.isKeyPressed(Input.Keys.A)) {
		paddlePosition.x -= maxVelocity * delta;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			paddlePositionHigh.x -= maxVelocity * delta;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.D)) {
		paddlePosition.x += maxVelocity * delta;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			paddlePositionHigh.x += maxVelocity * delta;
		}

		
		if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		}

		if(paddlePosition.x < 0.0) {
			paddlePosition.x = 0.0f;
		}else if (paddlePosition.x > Gdx.graphics.getWidth() - paddleWidth) {
			paddlePosition.x = Gdx.graphics.getWidth() - paddleWidth ;
		}
		if(paddlePositionHigh.x < 0.0) {
			paddlePositionHigh.x = 0.0f;
		}else if (paddlePositionHigh.x > Gdx.graphics.getWidth() - paddleWidthHigh) {
			paddlePositionHigh.x = Gdx.graphics.getWidth() - paddleWidthHigh ;
		}
		
		
		
		if(ballPosition.x <= ballRadius) {
			ballPosition.x = ballRadius;
			ballVelocity.x = -ballVelocity.x;
		}
		if(ballPosition.x >= Gdx.graphics.getWidth() ) {
			ballPosition.x = Gdx.graphics.getWidth() - ballRadius;
			ballVelocity.x = -ballVelocity.x;
		}
		if(ballPosition.y <= -ballRadius) {
	        ballPosition = new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() /2);
	        ballVelocity = new Vector2(random.nextFloat() * 200, 300.0f);
		}
		
		if(ballPosition.y >= Gdx.graphics.getHeight()) {
	        ballPosition = new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() /2);
	        ballVelocity = new Vector2(random.nextFloat() * 200, 300.0f);
		}
		
		
		

		checkCollisonWithLowerPaddle(paddlePosition, paddleWidth, paddleHeight);
		checkCollisonWithHigherPaddle(paddlePositionHigh, paddleWidthHigh, paddleHeightHigh);


		
		
		
		
		ballPosition.mulAdd(ballVelocity, delta);
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.GOLD);
        shapeRenderer.circle(ballPosition.x, ballPosition.y, ballRadius);
        shapeRenderer.rect(paddlePosition.x, paddlePosition.y, paddleWidth, paddleHeight);
        shapeRenderer.rect(paddlePositionHigh.x, paddlePositionHigh.y, paddleWidthHigh, paddleHeightHigh);;
        shapeRenderer.end();
	}
	
	@Override
	public void dispose() {
		shapeRenderer.dispose();
	}
	
	@Override
	public void hide() {
		this.dispose();
	}
	
	public void checkCollisonWithLowerPaddle(Vector2 paddlePosition, float paddleWidth, float paddleHeight) {
	    if (ballPosition.y + ballRadius >= paddlePosition.y &&
	        ballPosition.y - ballRadius <= paddlePosition.y + paddleHeight) {
	        if (ballPosition.x >= paddlePosition.x && 
	            ballPosition.x <= paddlePosition.x + paddleWidth) {
	            ballVelocity.y *= -1f; // Ball ändert die Richtung
	        }
	    }
	}

	public void checkCollisonWithHigherPaddle(Vector2 paddlePositionHigh, float paddleWidthHigh, float paddleHeightHigh) {
	    if (ballPosition.y + ballRadius >= paddlePositionHigh.y && 
	        ballPosition.y - ballRadius <= paddlePositionHigh.y + paddleHeightHigh) {
	        if (ballPosition.x >= paddlePositionHigh.x && 
	            ballPosition.x <= paddlePositionHigh.x + paddleWidthHigh) {
	            ballVelocity.y *= -1f; // Ball ändert die Richtung nur bei Kollision
	        }
	    }
	}


	
}
