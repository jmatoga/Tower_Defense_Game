package managers;

import Objects.Projectile;
import Objects.Tower;
import enemies.Enemy;
import help.Constants;
import help.LoadSave;
import scenes.Playing;

import java.awt.*;
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
        int totDist = xDist + yDist;

        float xPer = (float) xDist / totDist;

        float xSpeed = xPer * Constants.Projectiles.GetSpeed(type);
        float ySpeed = Constants.Projectiles.GetSpeed(type) - xSpeed;

        if(t.getX() > e.getX())
            xSpeed *= -1;
        if(t.getY() > e.getY())
            ySpeed *= -1;

        projectiles.add(new Projectile(t.getX()+25,t.getY()+25,xSpeed,ySpeed,projId++,type));
    }

    public void update() {
        for(Projectile p : projectiles)
            p.move();
    }

    public void draw(Graphics g) {
        for(Projectile p : projectiles)
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
