package org.bcit.comp2522.project.enemies;

public class Enemy_Fast_Var01 extends Enemy_Fast {
  protected static final String BASE_NAME_ENEM_FAST_VAR01 = "Spooky Ghost Hanji";
  protected static final int BASE_HP_ENEM_FAST_VAR01 = 2;
  protected static final int BASE_DMG_ENEM_FAST_VAR01 = 1;
  protected static final float BASE_SIZE_ENEM_FAST_VAR01 = 0.8f;
  protected static final float BASE_SPEED_ENEM_FAST_VAR01 = 1.5f;

  public Enemy_Fast_Var01(){
    this.name = BASE_NAME_ENEM_FAST_VAR01;
    this.health = BASE_HP_ENEM_FAST_VAR01;
    this.damage = BASE_DMG_ENEM_FAST_VAR01;
    this.size = BASE_SIZE_ENEM_FAST_VAR01;
    this.speed = BASE_SPEED_ENEM_FAST_VAR01;
  }

  public void collide(){
    // TODO: Ghost enemy, so ignore walls and always ahve straight line pathing
  }
}
