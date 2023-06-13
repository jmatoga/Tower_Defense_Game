package managers;

import Objects.Projectile;
import Objects.Tower;
import enemies.Enemy;
import help.Constants;
import help.LoadSave;
import scenes.Playing;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static help.Constants.Projectiles.*;
import static help.Constants.Towers.*;

public class ProjectileManager {
    private Playing playing;
    private ArrayList<Projectile> projectiles = new ArrayList<>();
    private BufferedImage[] proj_imgs;
    private BufferedImage hit_img;
    private int projId=0;
    private boolean drawHit;
    private Point2D.Float hitPos;

    public ProjectileManager(Playing playing) {
        this.playing = playing;
        importImgs();

    }

    private void importImgs() {
        // x: 1-6 y:2
        BufferedImage atlas = LoadSave.getSpriteResource();
        proj_imgs = new BufferedImage[6];

        for (int i = 0; i < 6; i++)
            proj_imgs[i] = atlas.getSubimage((1+i) *50,100,50,50);
        importHits(atlas);

    }

    private void importHits(BufferedImage atlas) {
        hit_img = atlas.getSubimage(50 * 7,100,50,50);
    }

    public void newProjectile(Tower t, Enemy e) {
        int type = getProjType(t);

        int xDist = (int) (t.getX() - e.getX());
        int yDist = (int) (t.getY() - e.getY());
        int totDist = Math.abs(xDist) + Math.abs(yDist);

        float xPer = (float) Math.abs(xDist) / totDist;

        float xSpeed = xPer * Constants.Projectiles.GetSpeed(type);
        float ySpeed = Constants.Projectiles.GetSpeed(type) - xSpeed;


        if(t.getX() > e.getX())
            xSpeed *= -1;
        if(t.getY() > e.getY())
            ySpeed *= -1;


        for (Projectile p : projectiles)
            if (!p.isActive())
                if (p.getProjectileType() == type) {
                    p.reuse(t.getX() + 25, t.getY() + 25, xSpeed, ySpeed, t.getDmg());
                    return;
                }

            projectiles.add(new Projectile(t.getX(),t.getY(),xSpeed,ySpeed,t.getDmg(),projId++,type));
        }

    public void update() {
        for(Projectile p : projectiles)
            if(p.isActive()) {
                p.move();
                if(isProjHittingEnemy(p)) {
                    p.setActive(false);
                    //drawHit = true;
                    //hitPos = p.getPos();
                }else if(isProjOutsideBounds(p)){
                    p.setActive(false);
                }
            }
        //drawHit = false;
    }

    private boolean isProjOutsideBounds(Projectile p) {
        if(p.getPos().x >= 0)
            if(p.getPos().x <= 1200)
                if(p.getPos().y >= 0)
                    if(p.getPos().y <= 950)
                        return false;

            return true;
    }

    private boolean isProjHittingEnemy(Projectile p) {
        for(Enemy e : playing.getEnemyManager().getEnemies()) {
            if(e.getBounds().contains(p.getPos())) {
                e.hurt(p.getDmg());
                return true;
            }
        }
        return false;
    }

    public void draw(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        for(Projectile p : projectiles)
            if(p.isActive()) {
                g2d.drawImage(proj_imgs[p.getProjectileType()], (int) p.getPos().x, (int) p.getPos().y, null);
                //g2d.setColor(Color.yellow);
                //g2d.drawRect((int) p.getPos().x, (int) p.getPos().y, 50, 50);
            }

        //drawHit(g2d);
    }

    private void drawHit(Graphics2D g2d) {
        g2d.drawImage(hit_img, (int)hitPos.x, (int)hitPos.y, null);
    }


    private int getProjType(Tower t) {
        switch (t.getTowerType()) {
            case BASIC, EXTRA:
                return BASIC_AMMO;
            case MACHINE, FAST_MACHINE:
                return MACHINE_AMMO;
            case BRUTAL, ONE_HIT:
                return HEAVY_AMMO;
            case CRAZY_CANNON:
                return CRAZY_AMMO;
            case DEUS_EX_MACHINA:
                return DEM_AMMO;
            case OWN_TOWER:
                // TODO
                return OWN_AMMO;
        }
        return 0;
    }

    public void reset(){
        projectiles.clear();
        projId = 0;
    }


//    public class Hit{
//
//        private Point2D.Float pos;
//        public Hit(Point2D.Float pos ){
//            this.pos = pos;
//        }
//
//        public void update(){
//
//        }
//
//        public Point2D.Float getPos(){
//            return pos;
//        }
//    }
}
