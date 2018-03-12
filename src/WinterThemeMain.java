import processing.core.PApplet;

/**
 * <h1>Recursion Winter Drawing</h1>
 	 * <h2>Recursion Shapes</h2>
	 	 * <li>Snowman.buttons(int num)</li>
	 	 * <li>Snowman.nose(int depth)</li>
	 	 * <li>Snowman.snowman(int num)</li>
	 * <h2>Recursion Methods</h2>
		 * <li>Environment.trees(int num, int numLeaves, float spacing, float offset)</li>
		 * <li>Environment.Tree.leaves(float x, float y, int depth)</li>
	 * <h2>Animation</h2>
	 	 * <li>Snow</li>
	 	 * <li>Snowman movement</li>
	 * <h2>Something Extra</h2>
	 	 * <li>Key press - Move snowman</li>
	 	 * <li>Mouse click - Toggle sunny environment</li>
	 * <h2>Last Modified</h2>
	 	 * <li>11:16PM 1/23/2018</li>
 * <br>
 *
 */
public class WinterThemeMain extends PApplet {
	
	private final int WIDTH = 1000;
	private final int HEIGHT = 1000;
	Snowman snowman = new Snowman(random(100,WIDTH-100), HEIGHT*0.825f, 15, 15, 3);
	Environment environment = new Environment();
	
	public static void main(String[] args) {
		PApplet.main("WinterThemeMain");
	}
	
	public void settings() {
		size(WIDTH, HEIGHT);
	}
	
	public void setup() {
		environment.draw();
	}
	
	public void draw() {
		environment.draw();
		snowman.draw();
		environment.snow();
	}
	
	public void keyPressed() {
		if(key == 'a') snowman.move(-5);
		if(key == 'd') snowman.move(5);
		if(key == CODED) {
			if(keyCode == LEFT) snowman.move(-5);
			if(keyCode == RIGHT) snowman.move(5);
		}
	}
	
	public void mouseClicked() {
		if(environment.isSunny()) environment.setSunny(false);
		else environment.setSunny(true);
	}
		
	class Snowman {
		
		private float x, y, w, h;
		private int layers;
		
		public Snowman(float x, float y, float w, float h, int layers) {
			this.x = x;
			this.y = y;
			this.w = w;
			this.h = h;
			this.layers = layers;
		}
		
		/**
		 * Move snowman
		 * Reset environment if snowman off screen
		 * @param speed
		 */
		public void move(int speed) {
			this.x += speed;
			if(x < w) {
				setX(WIDTH - w);
				environment.reset();
			}
			if(x > WIDTH - w) {
				setX(w);
				environment.reset();
			}
		}
		
		/**
		 * Draw snowman
		 */
		public void draw() {
			shadow();
			arms();
			snowman(3);
			buttons(3);
			hat();
			eyes();
			mouth();
			nose(5);
		}
		
		/**
		 * Draw snowman's body
		 * @param num Number of snowball layers
		 * @return
		 */
		public int snowman(int num) {
			if(num < 1) return 0;
			else {
				pushStyle();
				fill(Math.abs((num-1)*10-255));
				noStroke();
				ellipse(x, y+(num-1)*h*2, w+num*15, h+num*15);
				popStyle();
				return snowman(num-1);
			}
		}
		
		/**
		 * Draw snowman's buttons
		 */
		private int buttons(int num) {
			if(num < 1) return 0;
			else {
				pushStyle();
				fill(40);
				noStroke();
				ellipse(x, y+(num+1)*10, w/5, w/5);
				popStyle();
				return buttons(num - 1);
			}
		}
		
		/**
		 * Draw snowman's eyes
		 */
		private void eyes() {
			pushStyle();
			fill(40);
			noStroke();
			ellipse(x-5, y-5, w/5, h/5);
			ellipse(x+5, y-5, w/5, h/5);
			popStyle();
		}
		
		/**
		 * Draw snowman's hat
		 */
		private void hat() {
			pushStyle();
			fill(40);
			noStroke();
			rect(x-w*0.75f, y-h*2, w*1.5f, h);
			rect(x-w, y-h, w*2, 2);
			popStyle();
			pushStyle();
			fill(225,0,0);
			noStroke();
			rect(x-w*0.75f, y-h*1.4f, w*1.5f, h/2.5f);
			popStyle();
		}
		
		/**
		 * Draw snowman's mouth
		 */
		private void mouth() {
			pushStyle();
			fill(40);
			noStroke();
			ellipse(x-10f, y+3.5f, w/5, h/5);
			ellipse(x-5f, y+6.5f, w/5, h/5);
			ellipse(x, y+8f, w/5, h/5);
			ellipse(x+5f, y+6.5f, w/5, h/5);
			ellipse(x+10f, y+3.5f, w/5, h/5);
			popStyle();
		}
		
		/**
		 * Draw snowman's nose
		 * @param depth
		 * @return
		 */
		private int nose(int depth) {
			if(depth < 0) return 1;
			else {
				pushStyle();
				fill(255-depth*2, 175-depth*2, 100-depth*2);
				noStroke();
				ellipse(x, y+2f, w/10f*depth, h/10f*depth);
				popStyle();
				return nose(depth-1);
			}
		}
		
		/**
		 * Draw snowman's arms
		 */
		private void arms() {
			pushStyle();
			fill(150, 100, 100);
			noStroke();
			rectMode(CENTER);
			rect(x-(w*2), y+h*1.25f, w*2, h/4);
			rect(x+(w*2), y+h*1.25f, w*2, h/4);
			popStyle();
		}
		
		/**
		 * Draw snowman's shadow
		 */
		private void shadow() {
			pushStyle();
			fill(200);
			noStroke();
			ellipse(x, y+h*layers*2, w*5, 5);
			popStyle();
		}

		public float getX() {
			return x;
		}

		public void setX(float x) {
			this.x = x;
		}

		public float getY() {
			return y;
		}

		public void setY(float y) {
			this.y = y;
		}

		public float getW() {
			return w;
		}

		public void setW(float w) {
			this.w = w;
		}

		public float getH() {
			return h;
		}

		public void setH(float h) {
			this.h = h;
		}

		public int getLayers() {
			return layers;
		}

		public void setLayers(int layers) {
			this.layers = layers;
		}
	}
	
	class Environment {
		
		private int maxParticles, numTrees;
		private Particle[] particles;
		private float treeSpacing, treeOffset;
		private boolean sunny;
		
		public Environment() {
			maxParticles = 1000;
			setParticles();
			setTrees();
			sunny = true;
		}
		
		/**
		 * Draw environment
		 */
		public void draw() {
			sky();
			ground();
			trees(numTrees, 5, treeSpacing, treeOffset);
		}
		
		/**
		 * Reset particles and trees
		 */
		public void reset() {
			setParticles();
			setTrees();
			trees(numTrees, 5, treeSpacing, treeOffset);
		}
		
		/**
		 * Set tree values
		 */
		public void setTrees() {
			numTrees = parseInt(random(1,10));
			treeSpacing = random(100, 300);
			treeOffset = random(0, 300);
		}
		
		/**
		 * Construct a particle in every index in the particles array
		 */
		public void setParticles() {
			particles = new Particle[maxParticles];
			for(int i = 0; i < particles.length; i++) {
				particles[i] = new Particle(random(0, WIDTH), random(0, HEIGHT));
				particles[i].setSize(random(1,3));
			}
		}
		
		/**
		 * Draw the ground
		 */
		public void ground() {
			pushStyle();
			fill(255);
			noStroke();
			rect(0, HEIGHT*0.85f, WIDTH, HEIGHT*0.15f);
			popStyle();
		}
		
		/**
		 * Draw the sky
		 */
		public void sky() {
			if(sunny) {
				background(140, 160, 255);
				sun();
			} else {
				background(100);
				moon();
			}
		}
		
		/**
		 * Draw sun
		 */
		public void sun() {
			pushStyle();
			fill(220, 220, 100);
			noStroke();
			ellipseMode(CENTER);
			ellipse(WIDTH/2, 60, 50, 50);
			popStyle();
		}
		
		/** 
		 * Draw moon
		 */
		public void moon() {
			pushStyle();
			fill(200);
			noStroke();
			ellipseMode(CENTER);
			ellipse(WIDTH/2, 60, 50, 50);
			popStyle();
		}

		/**
		 * Draw recursive trees
		 * @param num Number of trees to draw
		 * @param spacing Measurement of space between each tree
		 * @param offset Offset from Zero X
		 * @param numLeaves Number of leaves to draw
		 * @return
		 */
		public int trees(int num, int numLeaves, float spacing, float offset) {
			Tree t = new Tree((num-1) * spacing + offset + 25, HEIGHT*0.6f, 32.5f, 300);
			t.shadow(t.getX()+t.getW()*0.75f, t.getY()+t.getH(), t.getW()*3, 5);
			t.trunk(t.getX()+t.getW()/4, t.getY(), t.getW(), t.getH());
			t.leaves(t.getX(), t.getY(), numLeaves);
			if(num > 1) return trees(num-1, numLeaves, spacing, offset); else return 0;
		}
		
		/**
		 * Draw falling snow
		 */
		public void snow() {
			for(Particle p : particles) {
				p.fall(0.6f);
				p.draw();
			}
		}
		
		public boolean isSunny() {
			return sunny;
		}

		public void setSunny(boolean sunny) {
			this.sunny = sunny;
		}

		class Tree {
			
			private float x, y, w, h;
			
			public Tree(float x, float y, float w, float h) {
				this.x = x;
				this.y = y;
				this.w = w;
				this.h = h;
			}
			
			/**
			 * Draw tree trunk
			 * @param x
			 * @param y
			 * @param w
			 * @param h
			 */
			private void trunk(float x, float y, float w, float h) {
				pushStyle();
				fill(150, 70, 70);
				noStroke();
				rect(x, y, w, h);
				popStyle();
			}
			
			/** 
			 * Draw tree shadow
			 * @param x
			 * @param y
			 * @param w
			 * @param h
			 */
			private void shadow(float x, float y, float w, float h) {
				pushStyle();
				fill(200);
				noStroke();
				ellipse(x, y, w, h);
				popStyle();
			}
			
			/**
			 * Draw recursive leaves
			 * @param x
			 * @param y
			 * @param depth
			 * @return
			 */
			private int leaves(float x, float y, int depth) {
				if(depth > 0) {
					pushStyle();
					fill(0, Math.abs(depth*-12.5f+170), 0);
					noStroke();
					float leafX1 = x + 25;
					float leafY1 = y - 100 - depth * -50;
					float leafX2 = x - 25;
					float leafY2 = y - depth * -50;
					float leafX3 = x + 75;
					float leafY3 = y - depth * -50;
					triangle(leafX1, leafY1, leafX2, leafY2, leafX3, leafY3);
					popStyle();
					return leaves(x, y, depth - 1);
				}
				return 0;
			}

			public float getX() {
				return x;
			}

			public void setX(float x) {
				this.x = x;
			}

			public float getY() {
				return y;
			}

			public void setY(float y) {
				this.y = y;
			}

			public float getW() {
				return w;
			}

			public void setW(float w) {
				this.w = w;
			}

			public float getH() {
				return h;
			}

			public void setH(float h) {
				this.h = h;
			}
		}
		
		class Particle {
			
			private float x, y, r, g, b, size;
			
			public Particle(float x, float y) {
				this.x = x;
				this.y = y;
				this.size = 10;
				setColor(255);
			}
			
			/**
			 * Draw particle
			 */
			public void draw() {
				pushStyle();
				fill(r, g, b);
				noStroke();
				ellipse(x, y, size, size);
				popStyle();
			}
			
			/**
			 * Particle moves down the screen
			 * @param speed
			 */
			public void fall(float speed) {
				this.y += speed;
				if(this.y > HEIGHT) reset();
			}
			
			/**
			 * Particle position is reset
			 */
			public void reset() {
				this.x = random(0, WIDTH);
				this.y = 0;
				setSize(random(1,3));
			}
			
			/**
			 * Set particle color to c
			 * @param c
			 */
			public void setColor(float c) {
				r = c;
				g = c;
				b = c;
			}
			
			/**
			 * Set particle color to (r, g, b)
			 * @param r
			 * @param g
			 * @param b
			 */
			public void setColor(float r, float g, float b) {
				this.r = r;
				this.g = g;
				this.b = b;
			}
			
			/**
			 * Set particle size
			 * @param size
			 */
			public void setSize(float size) {
				this.size = size;
			}
		}
	}
}
