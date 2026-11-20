# Mekanism Delight — DimensionWorks NeoForge 移植

保留原 mochi-753 Mekanism Delight 1.1.3 在本整合包实际启用的 18 个物品、合金果冻拼盘方块、15 条配方、辐射和料理效果；不是另一作者的同名模组。基线源码为 757f569d5097105082bcfae8de45b5417f51a039，原服务器 JAR 的配方目录已逐项对照。

原整包没有安装 Mekanism Tools 或 Evolved Mekanism，移植不编译其刀具材料适配；这些未启用内容不计作原档丢失。原通用合金扩展代码和可选资源保留。原三个基础果冻由拼盘取出取得，上游没有独立果冻配方；不凭物品名发明新配方。配方保留在 JSON 中，删除依赖旧平台和未安装附加包的 Java datagen。

Java 21、NeoForge 21.1.267、ModDevGradle 2.0.148、Gradle Wrapper 9.2.1。固定 Mekanism 5KzzycBT、Generators a6gl7srE 和 Farmer's Delight 原生版本。配方使用当前化学与 ItemStack codec、NeoForge 条件和 c 标签；料理奶昔通过原生药效移除事件保留取消语义，辐射使用当前实体能力。

`bash gradlew --no-daemon --max-workers=2 build runGameTestServer`：6 项必需 GameTest，验证原注册内容与完整组件往返、所有 15 条启用配方和材料标签、分级奶昔/效果取消、真实 Mek 辐射剂量以及拼盘三种份数顺序。服务器端口 25576、仅回环、2 GiB 与 2 CPU；测试类不打入生产 JAR。

原生专项通过不代表整个整包、实际客户端或旧存档已验收。一次性离线世界转换和完整整包部署继续由主仓库执行。按用户要求，不创建中间发行版；整包从固定源码提交和归档校验和构建。保留原 MIT LICENSE 与第三方资源的版权声明。
