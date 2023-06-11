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
    private int projId=0;

    public ProjectileManager(Playing playing) {
        this.playing = playing;
        importImgs();
    }

    private void importImgs() {
        // x: 1-6 y:2
        BufferedImage atlas = LoadSave.getSpriteResource();
        proj_imgs = new BufferedImage[6];
        for (int i = 0; i < 6; i++) {
            proj_imgs[i] = atlas.getSubimage((1+i) *50,100,50,50);

        }
    }

    public void newProjectile(Tower t, Enemy e) {
        int type = getProjType(t);
        int xDist = (int) Math.abs(t.getX() - e.getX());
        int yDist = (int) Math.abs(t.getY() - e.getY());
        int totDist = Math.abs(xDist) + Math.abs(yDist);

        float xPer = (float) Math.abs(xDist) / totDist;

        float xSpeed = xPer * Constants.Projectiles.GetSpeed(type);
        float ySpeed = Constants.Projectiles.GetSpeed(type) - xSpeed;

        if(t.getX() > e.getX())
            xSpeed *= -1;
        if(t.getY() > e.getY())
            ySpeed *= -1;

        // zeby nie zasypywac projectilelist juz aktywnymi projectiles i smieciami
        for(Projectile p : projectiles)
            if(!p.isActive() && p.getProjectileType() == type) {
                // t.getX()+4,t.getY()+3 - odpowiada za to gdzie sie pojawia strzal (ze srodka wiezy)
                p.reuse(t.getX()+4,t.getY()+3,xSpeed,ySpeed,t.getDmg());
                return;
            }
            // t.getX()+4,t.getY()+3 - odpowiada za to gdzie sie pojawia strzal (ze srodka wiezy)
            projectiles.add(new Projectile(t.getX()+4,t.getY()+3,xSpeed,ySpeed,t.getDmg(),projId++,type));
        }

    public void update() {
        for(Projectile p : projectiles)
            if(p.isActive()) {
                p.move();
                if(isProjHittingEnemy(p)) {
                    p.setActive(false);
                }
            }
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
        for(Projectile p : projectiles)
            if(p.isActive())
                g.drawImage(proj_imgs[p.getProjectileType()],(int)p.getPos().x,(int)p.getPos().y,null);
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
}
