package plugin.enemydown.command;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.SplittableRandom;

public class EnemyDownCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player){
            World world = player.getWorld();

            player.setHealth(20);
            player.setFoodLevel(20);

            world.spawnEntity(getEnemySpawnLocation(player, world), getEnemy());
        }
        return false;
    }



    /**
     * 敵の出現エリアを取得します
     * 出現エリアはx軸とz軸は自分の位置からプラス、ランダムで-10~9の値が設定されます。
     * y軸はプレイヤーと同じ位置になります。
     *
     * @param player　コマンドを実行したプレイヤー
     * @param world　コマンドを実行したプレイヤーが所属するワールド
     * @return 敵の出現場所
     */
    private Location getEnemySpawnLocation(Player player, World world) {
        Location playerLocation = player.getLocation();
        int randomX = new SplittableRandom().nextInt(20) - 10;
        int randomZ = new SplittableRandom().nextInt(20) - 10;

        double x = playerLocation.getX() + randomX;
        double y = playerLocation.getY();
        double z = playerLocation.getZ() + randomZ;

        return new Location(world, x, y, z);
    }

    /**
     * ランダムで敵を出現して、その結果の値を取得します。
     *
     * @return 敵
     */
    private EntityType getEnemy() {
        List<EntityType> enemyList = List.of(EntityType.ZOMBIE,EntityType.SKELETON);
        return enemyList.get(new SplittableRandom().nextInt(2));
    }
}
