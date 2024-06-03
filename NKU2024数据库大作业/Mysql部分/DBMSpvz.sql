CREATE DATABASE pvz;

USE pvz;

CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    account VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    age INT,
    gender VARCHAR(10),
    bio TEXT,
    role ENUM('common', 'VIP') NOT NULL DEFAULT 'common'
);

CREATE TABLE admin (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    account VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE plant (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    toughness INT,
    power INT,
    `range` VARCHAR(255),
    ammo VARCHAR(255),
    features TEXT,
    cost INT,
    cooldown INT,
    description TEXT,
    image_url VARCHAR(255)
);

CREATE TABLE zombie (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    toughness INT,
    equipment VARCHAR(255),
    bite_damage INT,
    throw_damage INT,
    crush_damage INT,
    speed VARCHAR(255),
    features TEXT,
    description TEXT,
    image_url VARCHAR(255)
);

CREATE TABLE ammo (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    damage INT,
    effect TEXT,
    image_url VARCHAR(255)
);

CREATE TABLE equipment (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    defense INT,
    damage INT,
    effect TEXT,
    image_url VARCHAR(255)
);
CREATE TABLE plant_comment (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    plant_id BIGINT,
    user_account VARCHAR(255),
    content TEXT,
    comment_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (plant_id) REFERENCES plant(id) ON DELETE CASCADE
);
CREATE TABLE zombie_comment (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    zombie_id BIGINT,
    user_account VARCHAR(255),
    content TEXT,
    comment_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (zombie_id) REFERENCES zombie(id) ON DELETE CASCADE
);
CREATE TABLE ammo_comment (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    ammo_id BIGINT,
    user_account VARCHAR(255),
    content TEXT,
    comment_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (ammo_id) REFERENCES ammo(id) ON DELETE CASCADE
);
CREATE TABLE equipment_comment (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    equipment_id BIGINT,
    user_account VARCHAR(255),
    content TEXT,
    comment_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (equipment_id) REFERENCES equipment(id) ON DELETE CASCADE
);

--含有事务应用的删除操作：
DELIMITER //

CREATE PROCEDURE deleteUserWithComments(
    IN p_user_id BIGINT
)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        -- 回滚事务
        ROLLBACK;
    END;

    -- 开始事务
    START TRANSACTION;

    -- 删除用户评论
    DELETE FROM plant_comment WHERE user_id = p_user_id;
    DELETE FROM zombie_comment WHERE user_id = p_user_id;
    DELETE FROM ammo_comment WHERE user_id = p_user_id;
    DELETE FROM equipment_comment WHERE user_id = p_user_id;

    -- 删除用户
    DELETE FROM users WHERE id = p_user_id;

    -- 提交事务
    COMMIT;
END //

DELIMITER ;

--触发器控制下的添加操作
DELIMITER //

CREATE TRIGGER before_insert_user
BEFORE INSERT ON users
FOR EACH ROW
BEGIN
    IF NEW.account = 'Admin' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Account cannot be Admin';
    END IF;
END //


CREATE PROCEDURE insertUser(
    IN p_name VARCHAR(255),
    IN p_account VARCHAR(255),
    IN p_password VARCHAR(255),
    IN p_age INT,
    IN p_gender VARCHAR(10),
    IN p_bio TEXT,
    IN p_role ENUM('common', 'VIP')
)
BEGIN
    INSERT INTO users (name, account, password, age, gender, bio, role)
    VALUES (p_name, p_account, p_password, p_age, p_gender, p_bio, p_role);
END //

DELIMITER ;


--存储过程控制下的更新操作DELIMITER //

CREATE PROCEDURE updateUserAndComments(
    IN p_id BIGINT,
    IN p_name VARCHAR(255),
    IN p_account VARCHAR(255),
    IN p_password VARCHAR(255),
    IN p_age INT,
    IN p_gender VARCHAR(10),
    IN p_bio TEXT,
    IN p_role ENUM('common', 'VIP')
)
BEGIN
    DECLARE v_old_account VARCHAR(255);

    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        -- 回滚事务
        ROLLBACK;
    END;

    -- 开始事务
    START TRANSACTION;

    -- 查询旧的 account 值
    SELECT account INTO v_old_account
    FROM users
    WHERE id = p_id;

    -- 更新用户表
    UPDATE users
    SET name = p_name, account = p_account, password = p_password, age = p_age, gender = p_gender, bio = p_bio, role = p_role
    WHERE id = p_id;

    -- 更新评论表中的 user_account
    UPDATE plant_comment
    SET user_account = p_account
    WHERE user_account = v_old_account;

    UPDATE zombie_comment
    SET user_account = p_account
    WHERE user_account = v_old_account;

    UPDATE ammo_comment
    SET user_account = p_account
    WHERE user_account = v_old_account;

    UPDATE equipment_comment
    SET user_account = p_account
    WHERE user_account = v_old_account;

    -- 提交事务
    COMMIT;
END //

DELIMITER ;


DELIMITER ;
--含有视图的查询操作
CREATE VIEW user_view AS
SELECT id, name, account, age, gender, bio, role FROM users;






-- 插入管理员数据
INSERT INTO admin (name, account, password) VALUES ('Admin', 'Admin', '2212000');
INSERT INTO plant (name, toughness, power, `range`, ammo, features, cost, cooldown, description, image_url)
VALUES
('豌豆射手', 300, 20, '前方一格', '单个豌豆', '普通攻击', 100, 1.5, '射击豌豆来攻击僵尸', 'image_url1'),
('向日葵', 300, 0, '无', '无', '生产阳光', 50, 7.5, '生产额外的阳光', 'image_url2'),
('樱桃炸弹', 300, 1800, '3x3范围', '爆炸', '大范围爆炸', 150, 50, '在一定范围内爆炸，瞬间摧毁敌人', 'image_url3'),
('坚果', 4000, 0, '无', '无', '阻挡僵尸前进', 50, 30, '阻挡僵尸前进，保护其他植物', 'image_url4'),
('土豆地雷', 300, 1800, '单格', '爆炸', '延时爆炸', 25, 30, '埋在地下，延时爆炸', 'image_url5'),
('雪花豌豆', 300, 20, '前方一格', '单个冰豌豆', '减速攻击', 175, 1.5, '射击冰豌豆来减速和攻击僵尸', 'image_url6'),
('噬鳄者', 300, 1500, '前方一格', '噬咬', '瞬间吞噬', 150, 50, '瞬间吞噬僵尸', 'image_url7'),
('双发豌豆', 300, 20, '前方两格', '双发豌豆', '普通攻击', 200, 1.5, '一次发射两颗豌豆', 'image_url8'),
('烟雾蘑菇', 300, 20, '前方五格', '烟雾', '穿透攻击', 75, 1.5, '发射穿透烟雾攻击', 'image_url9'),
('阳光蘑菇', 300, 0, '无', '无', '生产小阳光', 25, 7.5, '生产较少的阳光', 'image_url10'),
('胖小蘑菇', 300, 20, '前方一格', '爆炸', '范围爆炸', 75, 1.5, '爆炸攻击范围内的僵尸', 'image_url11'),
('火爆蘑菇', 300, 1800, '前方全屏', '爆炸', '全屏爆炸', 125, 50, '在整个屏幕范围内爆炸', 'image_url12'),
('墓碑吞噬者', 300, 20, '前方一格', '吞噬', '消除墓碑', 50, 1.5, '吞噬并消除墓碑', 'image_url13'),
('催眠蘑菇', 300, 20, '前方一格', '催眠', '催眠僵尸', 75, 1.5, '催眠并控制僵尸', 'image_url14'),
('冰蘑菇', 300, 0, '全屏', '冰冻', '全屏冰冻', 125, 50, '冰冻全屏僵尸', 'image_url15'),
('末日蘑菇', 300, 1800, '全屏', '爆炸', '全屏爆炸', 150, 50, '在整个屏幕范围内爆炸', 'image_url16'),
('睡莲', 300, 0, '无', '无', '水上种植', 25, 1.5, '允许在水上种植植物', 'image_url17'),
('倭瓜', 300, 1800, '单格', '压扁', '瞬间压扁', 50, 30, '瞬间压扁一个僵尸', 'image_url18'),
('三叶草', 300, 0, '全屏', '吹走', '吹走气球僵尸', 100, 50, '吹走全屏的气球僵尸', 'image_url19'),
('裂荚射手', 300, 20, '前方一格', '单发豌豆', '一次发射五个豌豆', 200, 1.5, '一次发射五个豌豆', 'image_url20'),
('杨桃', 300, 20, '前方五格', '星星', '五方向攻击', 125, 1.5, '向五个方向发射星星', 'image_url21'),
('南瓜', 300, 0, '无', '无', '保护植物', 125, 30, '包裹植物以保护', 'image_url22'),
('磁力菇', 300, 0, '前方一格', '吸引', '吸引金属物', 100, 1.5, '吸引并去除金属物', 'image_url23'),
('卷心菜投手', 300, 20, '前方一格', '卷心菜', '投掷攻击', 100, 1.5, '投掷卷心菜攻击', 'image_url24'),
('花盆', 300, 0, '无', '无', '屋顶种植', 25, 1.5, '允许在屋顶种植植物', 'image_url25');

INSERT INTO zombie (name, toughness, equipment, bite_damage, throw_damage, crush_damage, speed, features, description, image_url)
VALUES
('普通僵尸', 200, '无', 100, 0, 0, '慢', '无特殊能力', '普通僵尸，基本攻击', 'zombie_image_url1'),
('旗帜僵尸', 200, '无', 100, 0, 0, '慢', '加速其他僵尸', '旗帜僵尸，带有旗帜，能够加速其他僵尸', 'zombie_image_url2'),
('路障僵尸', 400, '路障', 100, 0, 0, '慢', '增加防御', '带有路障，增加防御能力', 'zombie_image_url3'),
('撑杆僵尸', 200, '撑杆', 100, 0, 0, '快', '跳跃能力', '能够跳跃过障碍物', 'zombie_image_url4'),
('铁桶僵尸', 600, '铁桶', 100, 0, 0, '慢', '高防御', '带有铁桶，防御能力极强', 'zombie_image_url5'),
('报纸僵尸', 200, '报纸', 100, 0, 0, '慢', '愤怒模式', '报纸被打破后进入愤怒模式，速度加快', 'zombie_image_url6'),
('铁栅门僵尸', 400, '铁栅门', 100, 0, 0, '慢', '高防御', '带有铁栅门，防御能力强', 'zombie_image_url7'),
('橄榄球僵尸', 800, '橄榄球服', 100, 0, 0, '快', '高防御，高攻击', '带有橄榄球服，防御和攻击力强', 'zombie_image_url8'),
('雪人僵尸', 400, '无', 100, 0, 0, '慢', '在低温下速度变快', '生命高，特性使其在寒冷地区移动速度更快', 'image_url9'),
('蹦极僵尸', 200, '无', 100, 0, 0, '快', '能够从空中降落', '能够从空中降落，直接攻击植物或拿走植物', 'image_url10'),
('扶梯僵尸', 500, '扶梯', 100, 0, 0, '慢', '能够使用扶梯跨越障碍', '带有扶梯，能够跨越高障碍物', 'image_url11'),
('投石车僵尸', 300, '投石车', 100, 200, 0, '慢', '远程攻击', '使用投石车远程攻击植物', 'image_url12'),
('伽刚特尔', 3000, '无', 300, 0, 0, '慢', '巨大的身躯和高攻击力', '具有极高的生命值和强大的攻击力', 'image_url13'),
('小鬼僵尸', 200, '无', 100, 0, 0, '快', '速度快，攻击力一般', '小巧灵活，移动速度快但攻击力一般', 'image_url14'),
('僵王博士', 6000, '无', 500, 0, 0, '慢', '最终BOSS', '游戏中的最终BOSS，具有强大的攻击力和生命值', 'image_url15'),
('豌豆射手僵尸', 200, '无', 100, 0, 0, '慢', '普通攻击', '普通攻击植物', 'image_url16'),
('坚果僵尸', 2000, '无', 100, 0, 0, '慢', '高防御', '高防御能力', 'image_url17'),
('僵尸', 200, '无', 100, 0, 0, '慢', '普通攻击', '普通攻击', 'image_url18'),
('垃圾桶僵尸', 400, '垃圾桶', 100, 0, 0, '慢', '高防御', '高防御能力', 'image_url19'),
('爆走伽刚特尔', 3000, '无', 300, 0, 0, '慢', '超强攻击', '具有极高的生命值和强大的攻击力', 'image_url20'),
('遛狗僵尸', 200, '无', 100, 0, 0, '快', '带有僵尸狗', '带有僵尸狗，具有快速度', 'image_url21'),
('僵尸狗', 290, '无', 150, 0, 0, '快', '高速度', '攻击力一般但速度很快', 'image_url22'),
('爆炸僵尸', 400, '无', 0, 0, 200, '慢', '自爆攻击', '接近植物后自爆攻击', 'image_url23'),
('标靶僵尸', 300, '无', 100, 0, 0, '慢', '普通攻击', '带有标靶，普通攻击', 'image_url24'),
('垃圾桶僵尸', 400, '垃圾桶', 100, 0, 0, '慢', '高防御', '带有垃圾桶，高防御能力', 'image_url25'),
('棍棒僵尸', 300, '无', 100, 0, 0, '快', '普通攻击', '带有棍棒，普通攻击', 'image_url26'),
('造床僵尸', 200, '无', 100, 0, 0, '慢', '高防御', '高防御能力', 'image_url27');



INSERT INTO ammo (name, damage, effect, image_url)
VALUES
('普通豌豆', 20, '普通攻击', 'pea_image_url1'),
('冰豌豆', 20, '减速攻击', 'ice_pea_image_url2'),
('火焰豌豆', 40, '火焰攻击', 'fire_pea_image_url3'),
('蘑菇弹', 50, '毒性攻击', 'mushroom_ammo_image_url4'),
('南瓜弹', 100, '大范围爆炸', 'pumpkin_ammo_image_url5');

INSERT INTO equipment (name, defense, damage, effect, image_url)
VALUES
('路障', 200, 0, '增加防御', 'equipment_image_url1'),
('铁桶', 400, 0, '高防御', 'equipment_image_url2'),
('铁栅门', 300, 0, '中等防御', 'equipment_image_url3'),
('橄榄球服', 800, 0, '高防御，高速度', 'equipment_image_url4');

INSERT INTO users (name, account, password, age, gender, bio, role)
VALUES
('刘思灼', '0000001', 'password1', 25, '男', '喜欢打篮球', 'common'),
('李子俊', '0000002', 'password2', 30, '女', '喜欢读书', 'VIP'),
('姬耀飞', '0000003', 'password3', 22, '男', '喜欢游泳', 'common'),
('宋禹衡', '0000004', 'password4', 28, '女', '喜欢旅行', 'VIP'),
('陈俊林', '0000005', 'password5', 26, '男', '喜欢音乐', 'common'),
('关忠含', '0000006', 'password6', 24, '女', '喜欢看电影', 'VIP'),
('卢乐飞', '0000007', 'password7', 27, '男', '喜欢健身', 'common'),
('左泽宇', '0000008', 'password8', 32, '女', '喜欢烹饪', 'VIP'),
('焦钰程', '0000009', 'password9', 29, '男', '喜欢跑步', 'common'),
('刘候君', '0000010', 'password10', 23, '女', '喜欢画画', 'VIP'),
('宋丞烨', '0000011', 'password11', 31, '男', '喜欢摄影', 'common'),
('韩雨辰', '0000012', 'password12', 28, '女', '喜欢写作', 'VIP'),
('窦文浩', '0000013', 'password13', 26, '男', '喜欢集邮', 'common'),
('王杨之', '0000014', 'password14', 24, '女', '喜欢舞蹈', 'VIP'),
('李嘉凯', '0000015', 'password15', 29, '男', '喜欢钓鱼', 'common'),
('郭星宇', '0000016', 'password16', 33, '女', '喜欢园艺', 'VIP'),
('朱振泽', '0000017', 'password17', 21, '男', '喜欢露营', 'common'),
('刘曹雨', '0000018', 'password18', 30, '女', '喜欢烘焙', 'VIP'),
('杨硕', '0000019', 'password19', 27, '男', '喜欢原神', 'common'),
('姬颖康', '0000020', 'password20', 27, '男', '喜欢睡觉', 'common'),
('李登科', '0000021', 'password21', 27, '男', '喜欢吃饭', 'common'),
('杨宇坤', '0000022', 'password22', 25, '女', '喜欢瑜伽', 'VIP');
