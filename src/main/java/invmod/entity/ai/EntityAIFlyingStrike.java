package invmod.entity.ai;

//NOOB HAUS:Done

import invmod.entity.Goal;
import invmod.entity.monster.EntityIMBird;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAIFlyingStrike extends EntityAIBase {
	private EntityIMBird theEntity;

	public EntityAIFlyingStrike(EntityIMBird entity) {
		this.theEntity = entity;
	}

	@Override
	public boolean shouldExecute() {
		return (this.theEntity.getAIGoal() == Goal.FLYING_STRIKE) || (this.theEntity.getAIGoal() == Goal.SWOOP);
	}

	@Override
	public boolean shouldContinueExecuting() {
		return this.shouldExecute();
	}

	@Override
	public void updateTask() {
		if (this.theEntity.getAIGoal() == Goal.FLYING_STRIKE)
			this.doStrike();
	}

	private void doStrike() {
		EntityLivingBase target = this.theEntity.getAttackTarget();
		if (target == null) {
			this.theEntity.transitionAIGoal(Goal.NONE);
			return;
		}

		float flyByChance = 1.0F;
		float tackleChance = 0.0F;
		float pickUpChance = 0.0F;
		if (this.theEntity.getClawsForward()) {
			flyByChance = 0.5F;
			tackleChance = 100.0F;
			pickUpChance = 1.0F;
		}

		float pE = flyByChance + tackleChance + pickUpChance;
		float r = this.theEntity.world.rand.nextFloat();
		if (r <= flyByChance / pE) {
			this.doFlyByAttack(target);
			this.theEntity.transitionAIGoal(Goal.STABILISE);
			this.theEntity.setClawsForward(false);
		} else if (r <= (flyByChance + tackleChance) / pE) {
			this.theEntity.transitionAIGoal(Goal.TACKLE_TARGET);
			this.theEntity.setClawsForward(false);
		} else {
			this.theEntity.transitionAIGoal(Goal.PICK_UP_TARGET);
		}
	}

	private void doFlyByAttack(EntityLivingBase entity) {
		this.theEntity.attackEntityAsMob(entity, 5);
	}
}