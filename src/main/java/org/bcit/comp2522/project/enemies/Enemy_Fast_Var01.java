package org.bcit.comp2522.project.enemies;

public class Enemy_Fast_Var01 extends Enemy_Fast {
  protected static final String NAME_ENEM_FAST_VAR01 = "Spooky Ghost Hanji";
  protected static final int HP_ENEM_FAST_VAR01 = 2;
  protected static final int DMG_ENEM_FAST_VAR01 = 1;
  protected static final float SIZE_ENEM_FAST_VAR01 = 0.8f;
  protected static final float SPEED_ENEM_FAST_VAR01 = 1.5f;

  public Enemy_Fast_Var01(){
    this.name = NAME_ENEM_FAST_VAR01;
    this.health = HP_ENEM_FAST_VAR01;
    this.damage = DMG_ENEM_FAST_VAR01;
    this.size = SIZE_ENEM_FAST_VAR01;
    this.speed = SPEED_ENEM_FAST_VAR01;
  }

  public void collide(){
    // TODO: Ghost enemy, so ignore walls and always ahve straight line pathing
  }
}
