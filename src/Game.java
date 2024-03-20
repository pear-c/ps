// Enemy 인터페이스
interface Enemy {
    void attack();
}

// Concrete 클래스들
class Zombie implements Enemy {
    @Override
    public void attack() {
        System.out.println("Zombie attacks!");
    }
}

class Vampire implements Enemy {
    @Override
    public void attack() {
        System.out.println("Vampire attacks!");
    }
}

class Ghost implements Enemy {
    @Override
    public void attack() {
        System.out.println("Ghost attacks!");
    }
}

// EnemyFactory 인터페이스
interface EnemyFactory {
    Enemy createEnemy();
}

// Concrete Factory 클래스들
class ZombieFactory implements EnemyFactory {
    @Override
    public Enemy createEnemy() {
        return new Zombie();
    }
}

class VampireFactory implements EnemyFactory {
    @Override
    public Enemy createEnemy() {
        return new Vampire();
    }
}

class GhostFactory implements EnemyFactory {
    @Override
    public Enemy createEnemy() {
        return new Ghost();
    }
}

// 게임 환경에서 사용하는 예
public class Game {
    public static void main(String[] args) {
        EnemyFactory factory;
        Enemy enemy;

        factory = new ZombieFactory();
        enemy = factory.createEnemy();
        enemy.attack();

        factory = new VampireFactory();
        enemy = factory.createEnemy();
        enemy.attack();

        factory = new GhostFactory();
        enemy = factory.createEnemy();
        enemy.attack();
    }
}
