#### Version: 0.3.2 (LibGDX 1.9.2, Artemis 1.3.1)

#### Version: 0.3.1 (LibGDX 1.9.2, Artemis 1.3.1)
- Updated to LibGDX 1.9.2 and Artemis 1.3.1
- **API Deprecated**: `SceneConfig#addSystem(BaseSystem system)`, `SceneConfig#addSystem(BaseSystem system, int priority)` and `SimpleSystemProvider`
    - Use `SceneConfig#addSystem(Class<? extends BaseSystem> system)` and `SceneConfig#Class<? extends BaseSystem> systemClass, int priority`
    - Adding system in 0.3.0: `parameter.config.addSystem(new MySystem())`
    - Adding system now: `parameter.config.addSystem(MySystem.class)`
    - If you need to pass custom arguments to system constructor implement `SystemProvider` directly.
- **API Change**: `Variables#variables` field is now final
- **API Change**: `SceneConfig.Priority` is now an enum (should not require any code change)
- **API Changes in `EntitySupport`**:
    - If you were using it to register custom renderer use `parameter.config.addSystem(SystemProvider)`
    - Removed `registerSystems(...)`
    - Added `registerSceneSystems(SceneConfig)`
- **API Addition**: `Variables#put`, `putInt`, `putFloat`, `putBoolean` methods for adding new variables
- **API Addition**: `Variables` copy constructors and `Variables#setFrom(Variables)` method
- **API Addition**: `Scene#getSceneVariables` returns scene variables set from VisEditor
- **API Addition**: new `Transform` constructors
- **Fixed**: `SystemProvider` interface is now public

#### Version: 0.3.0 (LibGDX 1.7.1, Artemis 1.2.1)
- API Change: `SceneParameter.systems` removed, added config field.
    - Adding system in 0.2.6: `parameter.systems.add(new MySystem())`
    - Adding system in 0.3.0: `parameter.config.addSystem(new MySystem())`
    - `SceneConfig` class provides additional methods for more detailed scene loading configuration
- `RuntimeConfiguration` changes:
    - **API Change**: `RuntimeConfiguration.useVisGroupManager` removed, if you set it to false use: `sceneParameter.config.disable(SceneFeature.GROUP_ID_MANAGER)`
    - **API Change**: `RuntimeConfiguration.useBox2dDebugRenderer` removed, if you set it to true use: `sceneParameter.config.enable(SceneFeature.BOX2D_DEBUG_RENDER_SYSTEM)`
    - **API Change**: `RuntimeConfiguration.useBox2dSpriteUpdateSystem` removed, if you set it to false use: `sceneParameter.config.disable(SceneFeature.PHYSICS_SPRITE_UPDATE_SYSTEM)`
- Changes in `SpriteComponent`:
    - Renamed to `VisSprite`
    - Uses `TextureRegion` directly
    - Properties like position, scale, rotation, are controlled by following components: `Transform`, `Origin`, `Tint`
- Changes in `TextComponent`:
    - Renamed to `VisText`
    - Properties like position, scale, rotation are controlled by following components: `Transform`, `Origin`, `Tint`
- Changes in `ParticleComponent`:
    - Renamed to `VisParticle`
    - Position is now controlled by `Transform` component.
- Changes in `SpriterComponent`:
    - Renamed to `VisSpriter`
    - Position and rotation is now controlled by `Transform` component.
- Renaming components:
    - `TextComponent` -> `VisText`
    - `SoundComponent` -> `VisSound`
    - `MusicComponent` -> `VisMusic`
    - `ParticleComponent` -> `VisParticle`
    - `SpriterComponent` -> `VisSpriter`
    - `SpineComponent` -> `VisSpine`
    - `SpriteComponent` -> `VisSprite`
    - `IDComponent` -> `VisID`
    - `GroupComponent` -> `VisGroup`
    - `VariablesComponent` -> `Variables`
    - `ShaderComponent` -> `Shader`
    - `RenderableComponent` -> `Renderable`
    - `PolygonComponent` -> `VisPolygon`
    - `PointComponent` -> `Point` (position is now stored in `Transform` component)
    - `PhysicsSpriteComponent` -> `PhysicsSprite`
    - `PhysicsPropertiesComponent` -> `PhysicsProperties`
    - `PhysicsComponent` -> `PhysicsBody`
    - `LayerComponent` -> `Layer`
    - `InvisibleComponent` -> `Invisible`
    - `AssetComponent` -> `AssetReference`
- **API Change**: Moved `RenderBatchingSystem`, `ParticleRenderSystem`, `SpriteRenderSystem`, `SpriterRenderSystem`, `TextRenderSystem` to `com.kotcrab.vis.runtime.system.render` package
- **API Change**: Moved `StoresAssetDescriptor`, `UsesProtoComponent` interfaces to `com.kotcrab.vis.runtime.properties package`
- **API Change**: Removed all `PropertiesAccessor` classes, now Components implements `*Owner` classes. For example every component that store position implements PositionOwner.
- **API Change**: Removed `ArtemisUtils`
- Updated to Artemis 1.2.1
    - **API Change**: `EntityEngineConfiguration` removed setSystem(BaseSystem system, boolean passive), use setSystem(BaseSystem system) instead
    - **API Change**: `EntityEngineConfiguration` removed setManager(Manager manager), use setSystem(BaseSystem system) instead
    - **API Change**: `EntityEngineConfiguration` removed getManager(Class), use getSystem(Class) instead
    - **API Change**: `SceneParameter` removed fields `managers` and `passiveSystem` fields, use `systems` instead

#### Old changelog file:
```
[0.2.6] [LibGDX 1.7.0] [Artemis 0.13.1]
-Fixed rotation of sprite ignored when creating physics body
-Box2d bodies are automatically disposed when entity is removed from EntityEngine (can be disabled in RuntimeConfiguration)

[0.2.5] [LibGDX 1.7.0] [Artemis 0.13.1]
-Updated to Artemis 0.13.1, LibGDX 1.7.0
-Added PointComponent
-Renamed PhysicsSettingsData to PhysicsSettings

[0.2.4] [LibGDX 1.6.4] [Artemis 0.10.2]
-Added Spriter support
-Fixed GWT support
-Improved scene loading errors logging
-When loading Scene without using VisAssetManager#loadSceneNow you must now manually cal Scene#init()
-Fixed wrong particle rendering when there was two or more particle effects of same kind

[0.2.3] [LibGDX 1.6.4] [Artemis 0.10.2]
-Added VariablesComponent
-API Addition: Scene#getLayerDataByName(String name)
-API Addition: SceneParameter#passiveSystems field - SceneParameter now supports adding passive systems

[0.2.2] [LibGDX 1.6.4] [Artemis 0.10.2]
-None, only VisEditor changes

[0.2.1] [LibGDX 1.6.4] [Artemis 0.10.2]
-LayerData is now saved in Scene
 -API Addition: Scene#getLayerData()
-Added VisGroupManager - allows to retrieve groups made in VisEditor
 -Can be disabled in RuntimeConfiguration
-Added Box2d physics support

[0.2.0] [LibGDX 1.6.4] [Artemis 0.10.2]
-First public release
```
