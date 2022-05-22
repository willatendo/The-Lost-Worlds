package lostworlds.server.util.registrate;

import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.tterrag.registrate.AbstractRegistrate;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.builders.ContainerBuilder;
import com.tterrag.registrate.builders.ContainerBuilder.ForgeContainerFactory;
import com.tterrag.registrate.builders.ContainerBuilder.ScreenFactory;
import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.util.NonNullLazyValue;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import com.tterrag.registrate.util.nullness.NonNullSupplier;

import lostworlds.client.books.BookBuilder;
import lostworlds.client.books.BookBuilder.SectionBuilder;
import lostworlds.client.books.BookBuilder.SectionBuilder.PageBuilder.ArchaeologyPageBuilder;
import lostworlds.client.books.BookBuilder.SectionBuilder.PageBuilder.PictureAndDescriptionPageBuilder;
import lostworlds.client.books.BookBuilder.SectionBuilder.PageBuilder.RecipePageBuilder;
import lostworlds.client.books.BookBuilder.SectionBuilder.PageBuilder.TextPageBuilder;
import lostworlds.client.sounds.LostWorldsSounds;
import lostworlds.server.LostWorldsTags;
import lostworlds.server.LostWorldsUtils;
import lostworlds.server.block.LostWorldsBlockModels;
import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.entity.utils.enums.DinoTypes;
import lostworlds.server.item.LostWorldsItems;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.AbstractBlock.Properties;
import net.minecraft.block.AbstractButtonBlock;
import net.minecraft.block.AbstractSignBlock;
import net.minecraft.block.Block;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.DoublePlantBlock;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.TrapDoorBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.client.gui.IHasContainer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Items;
import net.minecraft.loot.ConstantRange;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.conditions.BlockStateProperty;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.SoundDefinition;
import net.minecraftforge.common.data.SoundDefinition.Sound;
import net.minecraftforge.common.data.SoundDefinition.SoundType;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class LostWorldsRegistrate extends AbstractRegistrate<LostWorldsRegistrate> {
	public static final ProviderType<RegistrateBookProvider> BOOKS = ProviderType.register("books", (parent, event) -> new RegistrateBookProvider(parent, event.getGenerator()));
	public static final ProviderType<RegistrateSoundProvider> SOUNDS = ProviderType.register("sounds", (parent, event) -> new RegistrateSoundProvider(parent, event.getGenerator(), event.getExistingFileHelper()));

	protected LostWorldsRegistrate(String modid) {
		super(modid);
		this.addSounds();
		this.addBooks();
		this.extraLangStuff();
	}

	public static NonNullLazyValue<LostWorldsRegistrate> lazy(String modid) {
		return new NonNullLazyValue<>(() -> new LostWorldsRegistrate(modid).registerEventListeners(FMLJavaModLoadingContext.get().getModEventBus()));
	}

	public void addSounds() {
		this.addDataGenerator(SOUNDS, provider -> provider.addSound(LostWorldsSounds.MACHINE_WHIRLING.get(), SoundDefinition.definition().with(Sound.sound(LostWorldsSounds.MACHINE_WHIRLING.get().getLocation(), SoundType.SOUND)).subtitle("subtitle.block.machine_whirling")));
		this.addDataGenerator(SOUNDS, provider -> provider.addSound(LostWorldsSounds.POT_SMASH.get(), SoundDefinition.definition().with(Sound.sound(LostWorldsSounds.POT_SMASH.get().getLocation(), SoundType.SOUND)).subtitle("subtitle.block.pot_smash")));

		this.addDataGenerator(SOUNDS, provider -> provider.addSound(LostWorldsSounds.BIG_WALK.get(), SoundDefinition.definition().with(Sound.sound(LostWorldsSounds.BIG_WALK.get().getLocation(), SoundType.SOUND)).subtitle("subtitle.entity.big_walk")));
		this.addDataGenerator(SOUNDS, provider -> provider.addSound(LostWorldsSounds.MEDIUM_WALK.get(), SoundDefinition.definition().with(Sound.sound(LostWorldsSounds.MEDIUM_WALK.get().getLocation(), SoundType.SOUND)).subtitle("subtitle.entity.medium_walk")));
		this.addDataGenerator(SOUNDS, provider -> provider.addSound(LostWorldsSounds.SMALL_WALK.get(), SoundDefinition.definition().with(Sound.sound(LostWorldsSounds.SMALL_WALK.get().getLocation(), SoundType.SOUND)).subtitle("subtitle.entity.small_walk")));

		this.addDataGenerator(SOUNDS, provider -> provider.addSound(LostWorldsSounds.FOSSIL_POACHER_CELEBRATE.get(), SoundDefinition.definition().with(Sound.sound(LostWorldsSounds.FOSSIL_POACHER_CELEBRATE.get().getLocation(), SoundType.SOUND)).subtitle("subtitle.entity.fossil_poacher.celebrate")));
		this.addDataGenerator(SOUNDS, provider -> provider.addSound(LostWorldsSounds.FOSSIL_POACHER_DEATH.get(), SoundDefinition.definition().with(Sound.sound(LostWorldsSounds.FOSSIL_POACHER_DEATH.get().getLocation(), SoundType.SOUND)).subtitle("subtitle.entity.fossil_poacher.death")));
		this.addDataGenerator(SOUNDS, provider -> provider.addSound(LostWorldsSounds.FOSSIL_POACHER_GRUNT.get(), SoundDefinition.definition().with(Sound.sound(LostWorldsUtils.rL("entity.sound.fossil_poacher.grunt_1"), SoundType.SOUND)).with(Sound.sound(LostWorldsUtils.rL("entity.sound.fossil_poacher.grunt_2"), SoundType.SOUND)).with(Sound.sound(LostWorldsUtils.rL("entity.sound.fossil_poacher.grunt_3"), SoundType.SOUND)).subtitle("subtitle.entity.fossil_poacher.grunt")));
		this.addDataGenerator(SOUNDS, provider -> provider.addSound(LostWorldsSounds.FOSSIL_POACHER_HURT.get(), SoundDefinition.definition().with(Sound.sound(LostWorldsUtils.rL("entity.sound.fossil_poacher.hurt_1"), SoundType.SOUND)).with(Sound.sound(LostWorldsUtils.rL("entity.sound.fossil_poacher.hurt_2"), SoundType.SOUND)).subtitle("subtitle.entity.fossil_poacher.hurt")));
	}

	public void addBooks() {
		this.addDataGenerator(BOOKS, provider -> provider.addBook(new BookBuilder("field_guide", "Field Guide", "By Willatendo", 4546862).addSections(new SectionBuilder("modern", DinoTypes.GREAT_AUK.getSkeletonPick().get()).addPages(new TextPageBuilder("great_auk").addTitle("Pinguinus impennis - Great Auk").addFirst("The Great Auk, a large auk from the Neogene to Holocene. This large auk spaned across the North Pacific ocean. It had the odd predators like Polar Bears and Humans.").addNext("The Great Auk was 80 centimeters tall. It would've hunted fish. Humans traded thier parts all across North America and Europe.")).addPages(new TextPageBuilder("greak_auk_info").addFirst("Danger: Minimal").addNext("Diet: Piscivore").addNext("Range: Neogene - Late Holocene").addNext("Order: Charadriiformes").addNext("Family: Alcidae").addNext("Genus: Pinguinus"))).addSections(new SectionBuilder("cretaceous", DinoTypes.TYRANNOSAURUS.getSkeletonPick().get())
				/* Carnotaurus */
				.addPages(new TextPageBuilder("carnotaurus_1").addTitle("Carnotaurus sastrei").addFirst("Carnotaurus was medium theropod from South America. Found in the La Colonia Formation, the Carnotaurus would've feared other Carnotaurus and particularly feisty prey. First discovered in 1984, it was described in 1985.").addNext("Carnotaurus was 8 meters from nose to tail and would've presued anything it could get its jaws arround. Throwing its massive weight arround, this bulky creature would've been a feared predator.")).addPages(new TextPageBuilder("carnotaurus_2").addFirst("Few was safe from the massive creature. Carnotaurus's bones were built for shear strength, being able to run at massive speeds, though a rather slow turner.")).addPages(new TextPageBuilder("carnotaurus_info").addFirst("Danger: Timid").addNext("Diet: Carnivore").addNext("Range: Cretaceous - 71 mya").addNext("Clade: Dinosauria").addNext("Clade: Saurischia").addNext("Clade: Theropoda").addNext("Family: Abelisauridae").addNext("Clade: Furileusauria").addNext("Tribe: Carnotaurini").addNext("Genus: Carnotaurus"))
				/* Fukuivenator */
				.addPages(new TextPageBuilder("fukuivenator_1").addTitle("Fukuivenator paradoxus").addFirst("Fukuivenator, a small theropod from the Kitadani Formation in Cretaceous Japan. It was small theropod that picked on small prey, but would've much preferred shrubbery with flat teeth. A early Therizinosauria that via convergent evolution looked like a dromaeosaur. First discovered and described in 2016, in 2021 it was determined to be a basal member of Therizinosauria.").addNext("Fukuivenator was 2.45 meters from nose to tail, being rather small and would've feared larger preditors.")).addPages(new TextPageBuilder("fukuivenator_info").addFirst("Danger: Small").addNext("Diet: Omnivorous").addNext("Range: Cretaceous - 127-115 mya").addNext("Clade: Dinosauria").addNext("Clade: Saurischia").addNext("Clade: Theropoda").addNext("Clade: Maniraptoriformes").addNext("Clade: Maniraptora").addNext("Genus: Fukuivenator"))
				/* Giganotosaurus */
				.addPages(new TextPageBuilder("giganotosaurus_1").addTitle("Giganotosaurus carolinii").addFirst("Giganotosaurus, a massive theropod from the Candeleros Formation in Cretaceous Argentina. This massive species had no natural predators except other giganotosaurus. In only the worse scenarios, starvation. Giganotosaurus was first discovered in 1993 and described in 1995.").addNext("Giganotosaurus was 12 meters from nose to tail. The giganotosaurus would've hunted down anything it's jaws could fit around, even young titanosaurs weren't safe.")).addPages(new TextPageBuilder("giganotosaurus_info").addFirst("Danger: Massive").addNext("Diet: Carnivore").addNext("Range: Cretaceous - 127-115 mya").addNext("Clade: Dinosauria").addNext("Clade: Saurischia").addNext("Clade: Theropoda").addNext("Family: Carcharodontosauridae").addNext("Tribe: Giganotosaurini").addNext("Genus: Giganotosaurus"))
				/* Ouranosaurus */
				.addPages(new TextPageBuilder("ouranosaurus_1").addTitle("Ouranosaurus nigeriensis").addFirst("Ouranosaurus, a large hadrosaurus from Cretaceous Niger and Cameroon. This large hardrosaurus would've grazed through the green swamps like in the ancient Elrha Formation. Nigersaurus was a sauropod Ouranosaurus would've found itself living with. It was discovered in a expiditon from 1965 to 1972, led by French paleontologist Philippe Taquet.").addNext("Ouranosaurus was 7.2 meters from nose to tail. It would've found itself victem of the crocodyliformes and theropods in the formation.")).addPages(new TextPageBuilder("ouranosaurus_info").addFirst("Danger: Territorial").addNext("Diet: Herbivore").addNext("Range: Cretaceous - 112-125 mya").addNext("Clade: Dinosauria").addNext("Order: Ornithischia").addNext("Clade: Ornithopoda").addNext("Clade: Styracosterna").addNext("Clade: Hadrosauriformes").addNext("Genus: Ouranosaurus"))
				/* Parksosaurus */
				.addPages(new TextPageBuilder("parksosaurus_1").addTitle("Parksosaurus warreni").addFirst("Parksosaurus, a small neornithischian from the Upper Cretaceous Horseshoe Canyon Formation in Alberta, Canada. Due to the time it lived, Parksosaurus adapted legs capable of walking on the mud and clay from the sea in North America. This small herbivore would've been the food of marine reptiles living in the inland Sea.").addNext("Parksosaurus was around 2-2.5 meters in length, though there has been much debate about the size of this little hypsilophodont.")).addPages(new TextPageBuilder("parksosaurus_info").addFirst("Danger: None").addNext("Diet: Herbivore").addNext("Range - Cretaceous - 69.5 mya").addNext("Clade: Dinosauria").addNext("Order: Ornithischia").addNext("Family: Thescelosauridae").addNext("Genus: Parksosaurus"))
				/* Psittacosaurus */
				.addPages(new TextPageBuilder("psittacosaurus_1").addTitle("Psittacosaurus mongoliensis").addFirst("Psittacosaurus, a little ceratopsian from China, Mongolia, Siberia, and Thailand. This smart little ceratopsian, from calculations, had a rather large brain-to-body ratio and would've been active during the night and day. Psittacosaurus also had a rather well developed sense of smell and vision.").addNext("Psittacosaurus was almost 2 meters in length, with hatchlings found as small as 13 centimeters.")).addPages(new TextPageBuilder("psittacosaurus_info").addFirst("Danger: None").addNext("Diet: Herbivore").addNext("Range: Cretaceous - 126-101 mya").addNext("Clade: Dinosauria").addNext("Order: Ornithischia").addNext("Clade: Marginocephalia").addNext("Suborder: Ceratopsia").addNext("Family: Psittacosauridae").addNext("Genus: Psittacosaurus"))
				/* Suchomimus */
				.addPages(new TextPageBuilder("suchomimus_1").addTitle("Suchomimus tenerensis").addFirst("Suchomimus, a medium spinosaurid from the Elrha formation in Niger. Unlike spinosaurids, Suchomimus most likely was not aquatic, despite its swampy enviroment. In 2022, a study was done to study the chances of Spinosaurs being an aquatic organism via bone density. Suchomimus was found to have a high chance of being a land animal").addNext("Suchomimus was 9.5-11 meters long. It had a small dorsal sail, however it would've been little more than a hump. It ate fish and other small animals. It was discribed in 1997.")).addPages(new TextPageBuilder("suchomimus_info").addFirst("Danger: Small").addNext("Range: Piscivore - 125-112 mya").addNext("Clade: Dinosauria").addNext("Clade: Saurischia").addNext("Clade: Theropoda").addNext("Family: Spinosauridae").addNext("Clade: Ceratosuchopsini").addNext("Genus: Suchomimus"))
				/* Thanos */
				.addPages(new TextPageBuilder("thanos_1").addTitle("Thanos simonattoi").addFirst("Thanos, a abelisaur from Brazil, near São José do Rio Preto. In 2020, Thanos was named by the Brazillian team in honor of the Mad Titan from Marvel. Thanos was a medium carnivore, 5.5-6.5 meters in length.")).addPages(new TextPageBuilder("thanos_information").addFirst("Danger: Medium").addNext("Diet: Carnivore").addNext("Range: Cretaceous - 86–83 mya").addNext("Clade: Dinosauria").addNext("Clade: Saurischia").addNext("Clade: Theropoda").addNext("Family: Abelisauridae").addNext("Clade: Brachyrostra").addNext("Genus: Thanos"))
				/* Tyrannosaurus */
				.addPages(new TextPageBuilder("tyrannosaurus_1").addTitle("Tyrannosaurus rex").addFirst("Tyrannosaurus, sometimes referred to as Tyrannosaurus rex or T-rex, is the most easily recognizable dinosaurs, a major American find, it has been the antagonist of many films to date. Most famously, it was the icon for the Jurassic Park movies and books*.").addNext("Tyrannosaurus is often seen as a large lizard-like creature due to its popularity in media before we learned many non-avian dinosaurs, mainly theropods, had feathers. Though it is debated to the extent of the coverage, it's widely concitered")).addPages(new TextPageBuilder("tyrannosaurus_2").addFirst("young Tyrannosaurs were covered in down and may have retained feathers around their head into adulthood. Tyrannosaurus was discovered in 1905, when paleontology was still a rather new professional in the world. It originally was in the 'Kangaroo Pose,' a product of early paleontology.").addNext("In modern years, it has been challenged if the large creature was even a predator of any sorts, only being able to reach speeds of 25-40 kph. It has been purposed by many paleontology of varying practice that it may have been a scavenger.")).addPages(new TextPageBuilder("tyrannosaurus_3").addFirst("The two largest specimens are Sue and Scotty, with Sue being a longer specimen and Scotty being taller. Sue was found on indigenous land and was a long legal battle that ended with the finder of the bones spending 2 years in a federal prison, and Sue almost being sold into a private collection, which would've set back our knowledge of Tyrannosaurus many years.").addNext("Thanks to the work of McDonalds and Disney, Sue ended up in the care of the Discovery Center of Idaho. The increably well preserved specimen has taught us a lot about the Tyrannosaurs. A realistic")).addPages(new TextPageBuilder("tyrannosaurus_4").addFirst("recreation of Sue when she was alive is on display in the Discovery Center of Idaho.").addNext("A controversial paper was publised in 2022, potentially spliting the Tyrant Lizard King into 3 groups, adding Tyrannosaurus imperator and Tyrannosaurus regina to the list. However, this is not widely recognized at the moment.")).addPages(new TextPageBuilder("tyrannosaurs_info").addFirst("Danger: High").addNext("Diet: Carnivore").addNext("Range: Cretaceous - 68–66 mya").addNext("Clade: Dinosauria").addNext("Clade: Saurischia").addNext("Clade: Theropoda").addNext("Family: Tyrannosauridae").addNext("Subfamily: Tyrannosaurinae").addNext("Genus: Tyrannosaurus"))
				/* Utahraptor */
				.addPages(new TextPageBuilder("utahraptor_1").addTitle("Utahraptor ostrommaysi").addFirst("Utahraptor, a large dromaeosaur from Cretaceous North America. One of the largest dromaeosaurs, the first member was found in a well in Utah in 1975. The Utahraptor was a very stocky, heavily built creature. Utahraptor is most famous for being a raptor around the size of the Velociraptors in Jurassic Park, also being found in a simular place since in the film Dr Grant finds Velociraptor fossils in Utah.").addNext("5.5 meters long, weighing an estimated 300 kg, this dinosaur feared little.")).addPages(new TextPageBuilder("utahraptor_info").addFirst("Danger: Medium").addNext("Diet: Carnivore").addNext("Range: Cretaceous - 135–130 mya").addNext("Clade: Dinosauria").addNext("Clade: Saurischia").addNext("Clade: Theropoda").addNext("Family: Dromaeosauridae").addNext("Clade: Eudromaeosauria").addNext("Subfamily: Dromaeosaurinae").addNext("Genus: Utahraptor"))
				/* Zephyrosaurus */
				.addPages(new TextPageBuilder("zephyrosaurus_1").addTitle("Zephyrosaurus schaffi").addFirst("Zephyrosaurus, a small ornithischian from Cretaceous North America. Fossils have been found in Montana, with tracks in Maryland and Virginia. Little is known about Zephyrosaurus, with only parts of it known.")).addPages(new TextPageBuilder("zephyrosaurus_info").addFirst("Danger: None").addNext("Diet: Herbivore").addNext("Range: Cretaceous - 113 mya").addNext("Clade: Dinosauria").addNext("Clade: Ornithischia").addNext("Family: Thescelosauridae").addNext("Subfamily: Orodrominae").addNext("Genus: Zephyrosaurus"))).addSections(new SectionBuilder("jurassic", DinoTypes.ALLOSAURUS.getSkeletonPick().get())
						/* Allosaurus */
						.addPages(new TextPageBuilder("allosaurus_1").addTitle("Allosaurus fragilis").addFirst("Allosaurus was a large theropod from North America. It was most commonly found in the Morrison Formation. A massive apex predator, Allosaurus would've feared little than starvation and strong competition. The first specimens were discovered during the Bone Wars though, it was described in 1877.").addNext("Allosaurus was 12 meters from nose to tail. Allosaurus would've eaten anything it could get its jaws arround. Sauropods, pachycephalosaurus, nothing was safe apart from the largest of")).addPages(new TextPageBuilder("allosaurus_2").addFirst("Brachiosaurus. Allosaurus was thought to have hunted in packs to take down larger foe, making this theropod one of the scariest of creatures").addNext("One of the most complete and well known specimens, Big Al, has taught us a lot about Allosaurus's rugged life style. Big Al had sustained many injuries throught their relatively short 7-year life.").addNext("Some of Allosaurus's rivals were thought to be Ceratosaurus and Torvosaurus. Despite the generally weak bite force, Allosaurus could've inflicted very heavy bleeding to")).addPages(new TextPageBuilder("allosaurus_3").addFirst("anything caught between its jaws.").addNext("Should you find yourself face-to-face with a hungry or angry member of Allosaurus, you'd better run, fast.")).addPages(new TextPageBuilder("allosaurus_info").addFirst("Danger: Large").addNext("Diet: Carnivore").addNext("Range: Jurassic - 155-145 mya").addNext("Clade: Dinosauria").addNext("Clade: Saurischia").addNext("Clade: Theropoda").addNext("Family: Allosauridae").addNext("Subfamily: Allosaurinae").addNext("Genus: Allosaurus"))
						/* Chilesaurus */
						.addPages(new TextPageBuilder("chilesaurus").addTitle("Chilesaurus diegosuarezi").addFirst("Chilesaurus, a small ornithoscelida from Chile in the Uper Jurassic Toqui Formation. A mix of many taxonomy, including that of theropods, ornithischians, and sauropodomorphs, this little dinosaur is and oddity in paleontology. First confirmed discovery in 2004, described in 2015, the Chilesaurus has been place in many taxonomy.").addNext("Chilesaurus was 3.2 meters from nose to tail, around the size of a dog. It would've eaten smaller plants along the ground, though the teeth are that of a sterotypical theropod.")).addPages(new TextPageBuilder("chilesaurus_info").addFirst("Danger: Passive").addNext("Diet: Herbivore").addNext("Range: Jurassic - 165 mya").addNext("Clade: Dinosauria").addNext("Genus: Chilesaurus"))
						/* Cryolophosaurus */
						.addPages(new TextPageBuilder("cryolophosaurus").addTitle("Cryolophosaurus ellioti").addFirst("Cryolophosaurus, a medium theropod from Antarctica, from the Hanson Formation. A medium preditor, Cryolophosaurus would've feared other Cryolophosaurus and particularly feisty prey. First discovered in 1991, it was described in 1993.").addNext("Cryolophosaurus was 6 meters from nose to tail and would've presued anything it could get its jaws arround and would've been covered in feathers to combat the cold.")).addPages(new TextPageBuilder("cryolophosaurus_info").addFirst("Danger: Timid").addNext("Diet: Carnivore").addNext("Range: Jurassic - 190-186 mya").addNext("Clade: Dinosauria").addNext("Clade: Eusaurischia").addNext("Clade: Theropoda").addNext("Clade: Neotheropoda").addNext("Genus: Cryolophosaurus"))
						/* Dilophosaurus */
						.addPages(new TextPageBuilder("dilophosaurus").addTitle("Dilophosaurus wetherilli").addFirst("Dilophosaurus, a large theropod from North America. This large dinosaur is a fierce creature that would've been an early top predator. First confirmed discovery in 1940, described in 1954, the Dilophosaurus got a face-lift in early 2020, with a more correct frill.").addNext("Dilophosaurus was 7 meters from nose to tail. It would've hunted down large game, and would be a mighty fow, thought it was solitary.")).addPages(new TextPageBuilder("dilophosaurus_info").addFirst("Danger: Aggressive").addNext("Diet: Carnivore").addNext("Range: Jurassic - 193 mya").addNext("Clade: Dinosauria").addNext("Clade: Saurischia").addNext("Clade: Theropoda").addNext("Genus: Dilophosaurus"))
						/* Eustreptospondylus */
						.addPages(new TextPageBuilder("eustreptospondylus").addTitle("Eustreptospondylus oxoniensis").addFirst("Eustreptospondylus, a medium theropod from England. This large dinosaur was fierce carnivore. First discovered in 1870, it was described in 1871 with no name util 1890, when it was labled a species of Megalosaurus.").addNext("Eustreptospondylus was 6 meters from nose to tail.")).addPages(new TextPageBuilder("eustreptospondylus_info").addFirst("Danger: Aggressive").addNext("Diet: Carnivore").addNext("Range: Jurassic - 163–154 mya").addNext("Clade: Dinosauria").addNext("Clade: Saurischia").addNext("Clade: Theropoda").addNext("Family: Megalosauridae").addNext("Subfamily: Eustreptospondylinae").addNext("Genus: Eustreptospondylus"))
						/* Kentrosaurus */
						.addPages(new TextPageBuilder("kentrosaurus").addTitle("Kentrosaurus aethiopicus").addFirst("Kentrosaurus, a medium stegosaurid from Tanzania from the Tendaguru Formation. From stegosauridae, it's oftan thought to be a primitive stegosaur, but that is not the case, being closer related to Stegosaurus from the Morrison Formation. Discovered in 1909, described in 1915, it was a victim of WWII, with a lot of skeletons being destroyed.").addNext("Kentrosaurus was 4.6 meters from nose to tail, around the size of a large tigon. It would've been a grazer much like its North American cousin, Stegosaurus.")).addPages(new TextPageBuilder("kentrosaurus_info").addFirst("Danger: Timid").addNext("Diet: Herbivore").addNext("Range: Jurassic - 152 mya").addNext("Clade: Dinosauria").addNext("Order: Ornithischia").addNext("Suborder: Stegosauria").addNext("Family: Stegosauridae").addNext("Genus: Kentrosaurus"))
						/* Liaoningosaurus */
						.addPages(new TextPageBuilder("liaoningosaurus").addTitle("Liaoningosaurus paradoxus").addFirst("Liaoningosaurus, a small ankyolosaur from the Cretaceous. This small ankyolosaur is from the Yixian Formation in China. It wouldn't fear too much other than the larger carnivore that could break through it's strong back.").addNext("The only studied Liaoningosaurus fossil was 34 centimeters, but proven to only be 1 year old. It would've lived a standard lifestyle, grazing on small plants")).addPages(new TextPageBuilder("liaoningosaurus_info").addFirst("Danger: None").addNext("Diet: Herbivore").addNext("Range: Cretaceous - 122 mya").addNext("Clade: Dinosauria").addNext("Order: Ornithischia").addNext("Suborder: Ankylosauria").addNext("Clade: Euankylosauria").addNext("Family: Ankylosauridae").addNext("Genus: Liaoningosaurus"))
						/* Ostromia */
						.addPages(new TextPageBuilder("ostromia").addTitle("Ostromia crassipes").addFirst("Ostromia, a small anchiornithid from the Painten Formation of German. This dinosaur has a long history of identification. Discovered in 1855, it was orginally thought to have been a pterosaur, labeled Pterodactylus crassipes. In 1970, John Ostrom reexamined the bones and labled it as an Archaeopteryx. In 2017, it was identified much more like Anchiornis and was given the name Ostromia after Ostrom.").addNext("Ostromia only has fragmentary remains and size cannot be determined.")).addPages(new TextPageBuilder("ostromia_info").addFirst("Danger: None").addNext("Diet: Carnivore").addNext("Range: Jurassic - 150–145 mya").addNext("Clade: Dinosauria").addNext("Clade: Saurischia").addNext("Clade: Theropoda").addNext("Family: Anchiornithidae").addNext("Genus: Ostromia"))
						/* Ophthalmosaurus */
						.addPages(new TextPageBuilder("ophthalmosaurus").addTitle("Ophthalmosaurus icenicus").addFirst("Ophthalmosaurus, an ichthyosaur from the Jurassic, with fossils potentially from the Cretaceous. Ophthalmosaurus had famously large eyes, hence its name, and has been found in Eroupe and North America.")).addPages(new TextPageBuilder("ophthalmosaurus_info").addFirst("Danger: None").addNext("Diet: Piscivore").addNext("Range: Jurassic - 165–157 mya").addNext("Order: Ichthyosauria").addNext("Family: Ophthalmosauridae").addNext("Subfamily: Ophthalmosaurinae").addNext("Genus: Ophthalmosaurus"))
						/* Protosuchus */
						.addPages(new TextPageBuilder("protosuchus_1").addTitle("Protosuchus richardsoni").addFirst("Protosuchus, a crocodylomorph from Arazona, Nova Scotia, and South Africa. It was one meter long and weighed 40 kilograms.")).addPages(new TextPageBuilder("protosuchus_info").addFirst("Danger: Low").addNext("Diet: Carnivore").addNext("Range: Jurassic").addNext("Clade: Pseudosuchia").addNext("Superorder: Crocodylomorpha").addNext("Family: Protosuchidae").addNext("Genus: Palaeoniscum"))).addSections(new SectionBuilder("triassic", DinoTypes.PROCOMPSOGNATHUS.getSkeletonPick().get())
								/* Eoraptor */
								.addPages(new TextPageBuilder("eoraptor_1").addTitle("Eoraptor lunensis").addFirst("Eoraptor, a tiny sauropodomorph from trassic period South America. From the Ischigualasto Formation, this tiny dinosaur was previously thought to be a theropod, but upon further view, it was found to be a basal sauropodomorph. First discovered in 1991, it was described in 1993.").addNext("Eoraptor was 1.5 meters from nose to tail, around the size of a small dog. It would've hunted down small game and ate some plants on rare occasion")).addPages(new TextPageBuilder("eoraptor_info").addFirst("Danger: None").addNext("Diet: Omnivore").addNext("Range: Triassic - 231-228 mya").addNext("Clade: Dinosauria").addNext("Clade: Saurischia").addNext("Clade: Sauropodomorpha").addNext("Genus: Eoraptor"))
								/* Procompsognathus */
								.addPages(new TextPageBuilder("procompsognathus_1").addTitle("Procompsognathus triassicus").addFirst("Procompsognathus, a small coelophysid from Trassic Germany. The tiny coelophysid was discovered and described in 1906. It looks in many aspects as a larger Compsognathus, which came around in the Jurassic, but they are in no way related. The Procompsognathus is much less well known and researched than Compsognathus.").addNext("Procompsognathus was about one meter long, weighing 1 kilogram. It would've prayed upon small mammalian ancestors and small reptiles.")).addPages(new TextPageBuilder("procompsognathus_info").addFirst("Danger: None").addNext("Diet: Carnivore").addNext("Range: Triassic - 210 mya").addNext("Clade Dinosauria").addNext("Clade Saurischia").addNext("Clade: Theropoda").addNext("Family: Coelophysidae").addNext("Genus: Procompsognathus"))).addSections(new SectionBuilder("permian", DinoTypes.GORGONOPS.getSkeletonPick().get())
										/* Diictodon */
										.addPages(new TextPageBuilder("diictodon_1").addTitle("Diictodon feliceps").addFirst("Diictodon, a tiny therapsid from Zambia, South Africa, and China. A tiny burrower, Diictodon spent its days in burrows escaping predators like the gorgonopids it would've lived with. First discovered in 1991, it was described in 1993.").addNext("Diictodon was half a meter from nose to tail. It's considered to be one of the most effective herbivores of the Permian, spanning across much of Pangea. It made burrows up to 1.5 meters deep.")).addPages(new TextPageBuilder("diictodon_info").addFirst("Danger: None").addNext("Diet: Herbivore").addNext("Range: Permian - 259.8-254.1 mya").addNext("Clade: Synapsida").addNext("Clade: Therapsida").addNext("Clade: Dicynodontia").addNext("Family: Pylaecephalidae").addNext("Genus: Diictodon"))
										/* Dimetrodon */
										.addPages(new TextPageBuilder("dimetrodon_1").addTitle("Dimetrodon limbatus").addFirst("Dimetrodon, a large synapsid from the United States. A large, apex predator of the Early Permian. Filling many niches as it grew, in adulthood, it would've eaten most things it could find, especially Edaphosaurus. It was described in the 1870s. Though often lumped in with dinosaurs, it was neither a dinosaur or a reptile, more closely related to us than any dinosaur.").addNext("Dimetrodon was 4 meters from nose to tail. Eating anything that it could catch.")).addPages(new TextPageBuilder("dimetrodon_info").addFirst("Danger: Timid").addNext("Diet: Carnivore").addNext("Range: Permian - 295-272 mya").addNext("Clade: Synapsida").addNext("Family: Sphenacodontidae").addNext("Subfamily: Sphenacodontinae").addNext("Genus: Dimetrodon"))
										/* Edaphosaurus */
										.addPages(new TextPageBuilder("edaphosaurus_1").addTitle("Edaphosaurus pogonias").addFirst("Edaphosaurus, a medium synapsid from the North America and Europe. A medium herbivore, Edaphosaurus was discovered in described in 1912 and descrbed in 1931. Edaphosaurus was increably sucsessful, living from the mid Carboniferous to mid Permian.").addNext("Edaphosaurus was about 3 meters from nose to tail. Eating small plants and shrubs, has many species, varying in size and length.")).addPages(new TextPageBuilder("edaphosaurus_info").addFirst("Danger: Small").addNext("Diet: Herbivore").addNext("Range: Carboniferous - Permian - 303.4-272.5 mya").addNext("Clade: Synapsida").addNext("Clade: Sphenacomorpha").addNext("Family: Edaphosauridae").addNext("Genus: Edaphosaurus"))
										/* Gorgonops */
										.addPages(new TextPageBuilder("gorgonops_1").addTitle("Gorgonops torvus").addFirst("Gorgonops, a small gorgonopsid from the Permian South Africa. This average gorgonopsid had little predators other than larger gorgonopsids that shared it's habitat and starvation. First discovered in 1876 and described in 1890. The first ever species of Gorgonops, Gorgonops torvus, was from an skull found in Fort Beaufort, South Africa.").addNext("Gorgonops was 2 meters from nose to tail. It shared it's enviroment with a few other gorgonopsids, eating ")).addPages(new TextPageBuilder("gorgonops_info").addFirst("Danger: Medium").addNext("Diet: Carnivore").addNext("Range: Permian - 260-254 mya").addNext("Clade: Synapsida").addNext("Clade: Therapsida").addNext("Clade: Gorgonopsia").addNext("Family: Gorgonopsidae").addNext("Subfamily: Gorgonopsinae").addNext("Genus: Gorgonops"))
										/* Palaeoniscum */
										.addPages(new TextPageBuilder("palaeoniscum_1").addTitle("Palaeoniscum freieslebeni").addFirst("Palaeoniscum, a ray-fined fish from Permian Europe and Greenland. It was 30 centimeters long, and was discovered in 1818.")).addPages(new TextPageBuilder("palaeoniscum_info").addFirst("Danger: None").addNext("Diet: Carnivore").addNext("Range: Permian").addNext("Class: Actinopterygii").addNext("Order: Palaeonisciformes").addNext("Family: Palaeoniscidae").addNext("Genus: Palaeoniscum"))
										/* Rhinesuchus */
										.addPages(new TextPageBuilder("rhinesuchus_1").addTitle("Rhinesuchus whaitsi").addFirst("Rhinesuchus, a amphibian from the Permian South Africa. It was named after the Greek word for nose, though it is oftan thought it's named after the Rhine River.")).addPages(new TextPageBuilder("rhinesuchus_info").addFirst("Danger: Low").addNext("Diet: Carnivore").addNext("Range: Permian").addNext("Class: Amphibia").addNext("Order: Temnospondyli").addNext("Suborder: Stereospondyli").addNext("Family: Rhinesuchidae").addNext("Genus: Rhinesuchus"))).addSections(new SectionBuilder("cambrian", DinoTypes.ANOMALOCARIS.getSkeletonPick().get())
												/* Anomalocaris */
												.addPages(new TextPageBuilder("anomalocaris_1").addTitle("Anomalocaris canadensis").addFirst("Anomalocaris was large cambrian apex predator. Found in formations across Canada, China, Greenland, Australia, and Utah. Anomalocaris ate the bottem dwelling trilobites of the Cambrian ocean. Should you find yourself infront of one, you'll find no danger.").addNext("Anomalocaris was first discovered in 1886, it was described in 1892.").addNext("Anomalocaris was 2 meters from head to tail. Trilobies and other bottom feeders were the Anomalocaris's")).addPages(new TextPageBuilder("anomalocaris_2").addFirst("main food source. It's large mandibles could crack a trilobite in half before consumption. Using large, compact eyes atop of it's head, Anomalocaris scaned the ocean floor for food, with large, theorized, gill-like structures to each side.")).addPages(new TextPageBuilder("anomalocaris_info").addFirst("Danger: Low").addNext("Diet: Carnivore").addNext("Range: Cambrian - 520-499 mya").addNext("Class: Dinocaridida").addNext("Order: Radiodonta").addNext("Family: Anomalocarididae").addNext("Genus: Anomalocaris"))), provider));

		this.addDataGenerator(BOOKS, provider -> provider.addBook(new BookBuilder("lost_worlds_lexicon", "Lexicon", "By Willatendo", 3304264).addSections(new SectionBuilder("introduction", LostWorldsItems.LEATHER_BRUSH.get()).addPages(new TextPageBuilder("introduction").addTitle("Welcome to The Lost Worlds!").addFirst("A Minecraft dinosaur mod!").addNext("This Lexicon will tell you everything you need to know about the mod, from creating creatures to exploring ancient time eras.").addNext("The Lexicon is split into 4 major parts, with each provides information on a particular branch of the mod. They are organised in order of importance of information.")).addPages(new TextPageBuilder("note").addFirst("This book and mod are still not done! To ensure the quality and make everything the best it can be, we are a bit slow and want to ensure that everything is as quality and perfect as possible."))).addSections(new SectionBuilder("world", LostWorldsBlocks.JURASSIC_COBBLESTONE.get()).addPages(new TextPageBuilder("overview").addFirst("In the world section of the Lexicon, we will explore the paleontological and archaeological aspects of the world.").addNext("This chapter is split into three parts, paleontogy, archaeology, and other parts of the world. To the right, you'll find a table with quick links to each of the sections.")).addPages(new TextPageBuilder("archaeology").addTitle("Archaeology:").addFirst("IMPORTANT: ARCHAEOLOGY IS NOT DONE. COME BACK IN A FUTURE UPDATE FOR A COMPLETE GUIDE.").addNext("Archaeology is one of the many braches you can explore in the mod. Archaeology includes a wide range of things to do. Exploring, you will likely find many new things, from structures to the ruins of ancient civilizations.").addNext("Archaeology connects with the Time Traveling part of the mod.")).addPages(new TextPageBuilder("crystal_scarab_gems").addTitle("Crystal Scarab Gems:").addFirst("One of the additions for Archaeology portion of the mod is the Crystal Scarab Gem. A powerful relic of lost empires, the crystal scarab gem has the power of many, many tonnes of tnt.").addNext("They can be found in Fossil Poacher's homes, the black market. They can also be found in Bastion Reminent's tressure room. However, they will need to be reassembled as the years of being stolen and traded. They are found in six parts, four for their legs, one for the thorax, and one for the head.")).addPages(new ArchaeologyPageBuilder("crystal_scarab_gem", "crystal_scarab_gem")).addPages(new TextPageBuilder("charge_crystal_scarab_gem").addTitle("Charged Crystal Scarab Gems:").addFirst("In order to harness the full power of the gem, you'll need to strike it with lightning. A trident may be essental for creating one. This charges with gem with all the power it will need. According to the legends, anyway.").addNext("Then, after getting the full power of the gem, you can then use it to create the most powerful tools. The tools, according to legends, cannot be reparied in anyway. However, they have the ability to take out mountains. They are all capable of instant-mine of any of their blocks."))).addSections(new SectionBuilder("creature_creation", LostWorldsBlocks.CULTIVATOR.get()).addPages(new TextPageBuilder("where_to_start").addTitle("Creature Creation: Where to Start").addFirst("Where do you start when creating a creature? Well, the best place to start is exploring the world for fossils. They can be found everywhere around the world and will provide the DNA samples we need to create a extinct creature. Additionally, amber is another way get dinosaur DNA. Amber spawns like diamonds, while fossils can spawn under and above ground.").addNext("Before you head out, you'll need a few tools.")).addPages(new TextPageBuilder("paleontology_101").addTitle("Creature Creation: Paleontology").addFirst("When attempting to collect fossils, you'll need some different tools. For the fossils we want, we'll need a hammer. They can be aquired from a Black Market. Then, to collect the fossil and safely transport it, we'll need some wet paper.").addNext("Carefully, break the soft stone without fossil. You'll tell they are if they don't have a dark patch. You'll want to seperate the peaces with fossils to avoid the potental of them breaking others if they fall. Also, don't stand on them either.")).addPages(new TextPageBuilder("paleontology_102").addTitle("Creature Creation: Paleontology").addFirst("Left click with wet paper on a fossil after breaking it out of the stone to plaster it. It will pop off and we are ready for the next stage.")).addPages(new RecipePageBuilder("wet_paper_recipe", "wet_paper")).addPages(new TextPageBuilder("first_machines").addTitle("Creature Creation: First Machines").addFirst("Depending on whether or not what you've got is amber or fossils, where you'll want to begin will differ.").addNext("For obvious reasons, we'll start with fossils.").addNext("To start, you'll need to make a fossil cleaner. They are fairly cheap, but maybe a bit expensive for new players.")).addPages(new RecipePageBuilder("fossil_cleaner_recipe", "fossil_cleaner").addTitle("Fossil Cleaner")).addPages(new TextPageBuilder("fossil_cleaner").addTitle("Creature Creation: Fossil Cleaner").addFirst("Your fossil cleaner will require water to function. One water bucket will clean 3.5 fossils and water bottles will clean 0.1 fossils.").addNext("The fossil cleaner, like all machines, it will also require a redstone input. Redstone torches, redstone, blocks, and other power sources are recomended. However, of course, you'll also need a water source. Either a bucket or water bottle.").addNext("Players may find the fossil cleaner a cheeper machine.")).addPages(new RecipePageBuilder("fossil_grinder_recipe", "fossil_grinder").addTitle("Fossil Grinder")).addPages(new TextPageBuilder("fossil_grinder").addTitle("Creature Creation: Fossil Grinder").addFirst("The next stage is the fossil grinder. This is where most of your fossils will be wasted as it's very rare to find soft tissues. However, the waste can be used as bonemeal.").addNext("The fossil grinder just requires a redstone input. Once you have soft tissue, it's onto the next step.").addNext("Once again, new players may find the fossil grinder expensive as it requires a diamond, however it should be easy enough.")).addPages(new RecipePageBuilder("dna_extractor_recipe", "dna_extractor").addTitle("DNA Extractor")).addPages(new TextPageBuilder("dna_extractor").addTitle("Creature Creation: DNA Extractor").addFirst("Half-way though, we find the DNA Extractor. In the input slots, place a vile and the previously acquired soft tissue. Then, you'll get DNA.").addNext("The dna extractor requires a redstone input and a vile. Once you have dna, it's onto the fourth step.").addNext("The DNA Extractor is the cheapest of the machines and should be easily aquired.")).addPages(new RecipePageBuilder("analyzer_recipe", "analyzer").addTitle("Analyzer")).addPages(new TextPageBuilder("analyzer").addTitle("Creature Creation: Analyzer").addFirst("The penultimate stage is the analyzer. It will take a while, but the analyzer will copy all the dna onto a provided disc that will have 5 uses.").addNext("The analyser requires a redstone input and a disc. Once you have a dna disc, it's onto the last step.").addNext("Players may find the analyzer a cheeper machine.")).addPages(new RecipePageBuilder("dna_injector_recipe", "dna_injector").addTitle("DNA Injector")).addPages(new TextPageBuilder("dna_injector").addTitle("Creature Creation: DNA Injector").addFirst("The last stage is the DNA injector. This will inject the genetic code of a dinosaur into a chicken egg. This is the last step, not counting hatching.").addNext("The dna injector requires a redstone input and a chicken egg.").addNext("The DNA Injector is a fiarly cheap machines and should be easily made.")).addPages(new TextPageBuilder("hatching_eggs").addTitle("Creature Creation: Hatching Eggs").addFirst("The longest process is hatching an egg. It will take arround three days to hatch, like a turtle egg. Although it may take longer. All the while, the eggs are highly venerable to zombie attacks. It is imparative that you keep them safe.").addNext("Deepending on the creature, they may stack differently. Large eggs stack to 3, medium 6, small 10, and tiny 19.").addNext("They need to be placed on a nesting block.")).addPages(new TextPageBuilder("nesting_block").addTitle("Nesting Block").addFirst("A block to keep your eggs in perfect state while they hatch. Infact, they won't hatch without one!")).addPages(new RecipePageBuilder("nesting_block_recipe", "nesting_block").addTitle("Nesting Block")).addPages(new PictureAndDescriptionPageBuilder("nest_example", "lostworlds:textures/gui/book/mountains_nest.png", 180, 87).addFirst("A example of Kentrosaurus Eggs in a mountain biome. Depending on your config, they take on the biome colour unless choosen else.")).addPages(new TextPageBuilder("but_what_about_plants").addTitle("But What About Plants?").addFirst("Don't worry, they're almost exactly the same! Of course, instead of dinosaur fossils, you use bark (can be collected from petrified wood via stripping it) and plant fossils. However, you'll skip the fossil cleaner stage and the dna injector one.").addNext("Instead, you'll place it in the last machine, the cultivator. It will grow the DNA into either a sapling or seed! Of course, it just need a redstone signal.")).addPages(new RecipePageBuilder("cultivator_recipe", "cultivator").addTitle("Cultivator Recipe"))).addSections(new SectionBuilder("creature_care", Items.WHEAT)).addSections(new SectionBuilder("time_traveling", LostWorldsItems.JURASSIC_PERIOD_TIME_BOOK.get())), provider));
	}

	public void copyTag(LostWorldsTags.ModBlockTags modBlockTag, LostWorldsTags.ModItemTags modItemTag) {
		this.addDataGenerator(ProviderType.ITEM_TAGS, provider -> provider.copy(modBlockTag.tag, modItemTag.tag));
	}

	public void extraLangStuff() {
		this.addDescription("lost_worlds_lexicon", "An Everything-You-Need-To-Know Book!");
		this.addDescription("field_guide", "Willatendo - Volume 3");
		this.addDescription("collectible", "It's a Collectible!");

		this.tabletLang("age", "Age:");
		this.tabletLang("baby", "Baby");
		this.tabletLang("adult", "Adult");
		this.tabletLang("heath", "Heath:");
		this.tabletLang("hunger", "Hunger:");
		this.tabletLang("full", "Full");
		this.tabletLang("satiated", "Satiated");
		this.tabletLang("hungry", "Hungry");
		this.tabletLang("starving", "Starving");
		this.tabletLang("diet", "Diet:");
		this.tabletLang("herbivore", "Herbivore");
		this.tabletLang("carnivore", "Carnivore");
		this.tabletLang("activity_type", "Activity Type:");
		this.tabletLang("cathemeral", "Cathemeral");
		this.tabletLang("nocturnal", "Nocturnal");
		this.tabletLang("crepuscular", "Crepuscular");
		this.tabletLang("diurnal", "Diurnal");
		this.tabletLang("contraceptives", "Contraceptives:");
		this.tabletLang("on_contraceptives", "On");
		this.tabletLang("off_contraceptives", "Off");
		this.tabletLang("tagged_to", "Tagged To:");

		this.addRawLang("item.lostworlds.scarab_banner_pattern.desc", "Scarab");
		for (DyeColor colour : DyeColor.values()) {
			this.addRawLang("block.minecraft.banner.scarab." + colour.getName(), Arrays.stream(colour.getName().toLowerCase(Locale.ROOT).split("_")).map(StringUtils::capitalize).collect(Collectors.joining(" ")) + " Scarab");
		}

		this.addRawLang("effect.lostworlds.ashy_lung_effect", "Ashy Lung");

		this.addRawLang("enchantment.lostworlds.precision.desc", "Reduces the chance of breaking a fossil.");
		this.addRawLang("enchantment.lostworlds.curse_of_breaking.desc", "Increases the chance of breaking a fossil.");

		this.addRawLang("jei.lostworlds.charged_crystal_scarab_gem", "Created in a lightning strike, they are one of the rarest and most valuable items in the game. They are require for a lot of things, including time travel. Made with a Crystal Scarab Gem.");
		this.addRawLang("jei.lostworlds.fossil_cleaner.title", "Fossil Cleaning");
		this.addRawLang("jei.lostworlds.fossil_cleaner_fuel.title", "Fossil Cleaner Fuel");
		this.addRawLang("jei.lostworlds.cleanCount.single", "Cleans 1 Item");
		this.addRawLang("jei.lostworlds.cleanCount.multi", "Cleans %s Items");
		this.addRawLang("jei.lostworlds.fossil_grinder.title", "Fossil Grinding");
		this.addRawLang("jei.lostworlds.fossil_grinder.chance", "Chance:");
		this.addRawLang("jei.lostworlds.dna_extractor.title", "DNA Extracting");
		this.addRawLang("jei.lostworlds.analyzer.title", "Analyzing");
		this.addRawLang("jei.lostworlds.dna_injector.title", "DNA Injecting");
		this.addRawLang("jei.lostworlds.cultivator.title", "Cultivating");
		this.addRawLang("jei.lostworlds.archaeology_table.title", "Archaeology");
		this.addRawLang("jei.lostworlds.lightning.title", "Lightning Strike");
		this.addRawLang("jei.lostworlds.paleontology_table.title", "Paleontology");
		this.addRawLang("jei.lostworlds.time_machine.title", "Time Enchanting");

		this.addRawLang("death.attack.ashyLung", "%1$s's lungs filled up with ash");
		this.addRawLang("death.attack.suffication", "%1$s sufficated");
		this.addRawLang("death.attack.hunger", "%1$s died of Hunger");
		this.addRawLang("death.attack.prick", "%1$s was pricked to death with a needle");

		this.addRawLang("entity.minecraft.villager.lostworlds.archaeologist", "Archaeologist");
		this.addRawLang("entity.minecraft.villager.lostworlds.paleontologist", "Paleontologist");

		this.addRawLang("event.lostworlds.load_dev_build", "You are running %s: Dev Edition. Do not redistribute.");
		this.addRawLang("event.lostworlds.load_snapshot_build", "You are running an %s snapshot. Do not redistribute and report any and all bugs.");
		this.addRawLang("event.lostworlds.load_youtube_build", "Thank you for playing with the mod! I greatly appreciate the distrabution of the mod. You are running Youtube Version %s.");
		this.addRawLang("event.lostworlds.load", "Thank you for downloading the mod! You are running Public %s.");

		this.addRawLang("timeBook.lostworlds.doesnt_work", "Time doesn't exist in this dimension!");
		this.addRawLang("timeBook.lostworlds.transport_to_overworld", "Transporting %s back home!");
		this.addRawLang("timeBook.lostworlds.transport_to_permian_period", "Transporting %s to the Permian Period!");
		this.addRawLang("timeBook.lostworlds.transport_to_jurassic_period", "Transporting %s to the Jurassic Period!");
		this.addRawLang("timeBook.lostworlds.transport_to_cretaceous_period", "Transporting %s to the Cretaceous Period!");

		this.addRawLang("filled_map.surface_fossil", "Fossil Map");
		this.addRawLang("filled_map.subterranean_fossil", "Fossil Map");

		this.advancement("root", "Lost Worlds", "Welcome to the Lost Worlds!");
		this.advancement("a_terrible_market", "A Terrible Market", "Find a Black Market. A place full of greedy pillagers, but more importantly, a hammer.");
		this.advancement("basic_explorer", "Basic Explorer", "Just the start of your exploring journey.");
		this.advancement("fossils", "Fossils", "Break your first fossil! Soon, you'll have a museum or a park or a battle arena!");
		this.advancement("decoration", "Decoration", "May not bring back creatures, but still cool!");
		this.advancement("in_the_field", "In The Field", "Aquire the field guide! A paleontologist's book!");
		this.advancement("just_a_remnant", "Just a Remnant", "Find a fossil. The last signs of species long lost.");
		this.advancement("just_a_trace", "Just a Trace", "Aquire the soft tissue of an extinct creature.");
		this.advancement("like_indiana_jones", "Like Indiana Jones", "Just the start of archaeology. Soon, you'll be like the famous Indiana Jones!");
		this.advancement("dangerous_relic", "Dangerous Relic", "The broken heart of ancient civilizations! When brought together, it has the power of 8 kilatons of tnt.");
		this.advancement("lost_heart", "Lost Heart", "The heart of ancient civilizations. One of the most powerful and dangerous items of the world!");
		this.advancement("charged", "Charged", "You've done it! It's more powerful then ever! If only it could come into contact with netherite...");
		this.advancement("executioner_style", "Executioner-Style", "The strongest axe on the planet, perfect for anything. After a lot of work!");
		this.advancement("sharpest_sword", "Sharpest Sword", "Kills a wither in a few swings!");
		this.advancement("shovel_thats_all", "Shovel...That's All", "No puns or cleverness.");
		this.advancement("strongest_pick", "The Strongest Pick", "The strongest pickaxe on the planet, who could possibly break it!");
		this.advancement("ungodly_dedication", "Ungodly Dedication", "To whomever completed this in survival, how?");
		this.advancement("paleobotany", "Paleobotany", "Plants are cool! Ancient plants are cooler!");
		this.advancement("petrified_trees", "Petrified Trees", "You found the remains of an ancient tree!");
		this.advancement("plants_at_least_they_dont_kill", "Plants! At Least They Don't Kill...", "Plants may be an odd choise to bring back, but they don't kill people!");
		this.advancement("ancient_seeds", "Ancient Seeds", "After lots of work, you got some seeds!");
		this.advancement("scratching_trees", "Scratching Trees", "You stripped a petrified tree! Soon, they'll be extant again!");
		this.advancement("long_lost_trees", "Long Lost Trees", "Bring back a dead tree!");
		this.advancement("the_broken_kt_boundry", "The Broken K-T Boundry", "Aquire the DNA of an ancient creature!");
		this.advancement("digitalization", "Digitalization", "Write DNA to a disc!");
		this.advancement("dr_frankenstein", "Dr Frankenstein", "Aquire an egg, spawn, or a syringe!");
		this.advancement("the_museum", "The Museum", "You aquired all you need to make a museum, a paleontology table!");
		this.advancement("for_the_masses", "For The Masses", "You started articulating a skeleton!");
		this.advancement("the_time_traveller", "The Time Traveller", "Travelled to every time era in the mod.");
		this.advancement("the_cretaceous", "The Cretaceous", "Acquire volume 3 of the Time Lord's work!");
		this.advancement("cretaceous_explorer", "Cretaceous Explorer", "Explore every biome in the Cretaceous!");
		this.advancement("the_jurassic", "The Jurassic", "Acquire volume 2 of the Time Lord's work!");
		this.advancement("jurassic_explorer", "Jurassic Explorer", "Explore every biome in the Jurassic!");
		this.advancement("the_permian", "The Permian", "Acquire volume 1 of the Time Lord's work!");
		this.advancement("permian_explorer", "Permian Explorer", "Explore every biome in the Permian!");
		this.advancement("time_travelling", "Time Travelling", "Just like Doc, you'll find yourself lost in time! Or, maybe, you'll be a lord of it!");
	}

	public void addDescription(String item, String desc) {
		this.addRawLang("item.lostworlds." + item + ".desc", desc);
	}

	public void tabletLang(String stat, String value) {
		this.addRawLang("tablet.lostworlds." + stat, value);
	}

	public void subtitle(String sound, String value) {
		this.addRawLang("subtitle." + sound, value);
	}

	public void advancement(String advancement, String title, String desc) {
		this.addRawLang("advancement.lostworlds." + advancement + ".title", title);
		this.addRawLang("advancement.lostworlds." + advancement + ".desc", desc);
	}

	@Override
	public <T extends Container, SC extends Screen & IHasContainer<T>> ContainerBuilder<T, SC, LostWorldsRegistrate> container(String name, ForgeContainerFactory<T> factory, NonNullSupplier<ScreenFactory<T, SC>> screenFactory) {
		this.addRawLang("container.lostworlds." + name, Arrays.stream(name.toLowerCase(Locale.ROOT).split("_")).map(StringUtils::capitalize).collect(Collectors.joining(" ")));
		return super.container(name, factory, screenFactory);
	}

	public <T extends Block> BlockBuilder<T, LostWorldsRegistrate> blockAndItem(String name, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).simpleItem();
	}

	public <T extends Block> BlockBuilder<T, LostWorldsRegistrate> leaves(String name, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).color(() -> LostWorldsBlocks::getGrassyColour).blockstate((block, provider) -> provider.getVariantBuilder(block.get()).partialState().setModels(new ConfiguredModel(provider.models().withExistingParent(block.getName(), provider.mcLoc("block/leaves")).texture("all", provider.modLoc("block/" + name))))).item().color(() -> LostWorldsBlocks::getGrassyItemColour).build();
	}

	public <T extends Block> BlockBuilder<T, LostWorldsRegistrate> blockItemModel(String name, String parent, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).item().model((item, provider) -> provider.withExistingParent(item.getName(), provider.modLoc("block/" + parent))).build();
	}

	public <T extends Block> BlockBuilder<T, LostWorldsRegistrate> blockItemFlat(String name, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).item().model((item, provider) -> provider.generated(() -> item.get(), provider.modLoc("item/" + name))).build();
	}

	public <T extends Block> BlockBuilder<T, LostWorldsRegistrate> blockItemFlat(String name, String texture, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).item().model((item, provider) -> provider.generated(() -> item.get(), provider.modLoc("item/" + texture))).build();
	}

	public <T extends Block> BlockBuilder<T, LostWorldsRegistrate> blockItemFlatColoured(String name, String texture, DinoTypes types, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).item().model((item, provider) -> provider.generated(() -> item.get(), provider.modLoc("item/" + texture))).color(() -> () -> LostWorldsBlocks.getEggItem(types)).build();
	}

	public <T extends Block> BlockBuilder<T, LostWorldsRegistrate> sapling(String name, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> provider.getVariantBuilder(block.get()).partialState().setModels(new ConfiguredModel(provider.models().cross(block.getName(), provider.modLoc("block/" + name))))).item().model((item, provider) -> provider.generated(() -> item.get(), provider.modLoc("block/" + name))).build();
	}

	public <T extends Block> BlockBuilder<T, LostWorldsRegistrate> plantColoured(String name, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> provider.getVariantBuilder(block.get()).forAllStates(state -> {
			return ConfiguredModel.builder().modelFile(provider.models().withExistingParent(block.getName(), provider.modLoc("block/template_two_part_cross")).texture("stem", "block/" + block.getName() + "_stem").texture("leaves", "block/" + block.getName() + "_leaves")).build();
		})).item().model((item, provider) -> provider.generated(() -> item.get(), provider.modLoc("block/" + name))).build();
	}

	public <T extends DoublePlantBlock> BlockBuilder<T, LostWorldsRegistrate> doublePlantColoured(String name, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> provider.getVariantBuilder(block.get()).forAllStates(state -> {
			if (state.getValue(DoublePlantBlock.HALF) == DoubleBlockHalf.LOWER) {
				return ConfiguredModel.builder().modelFile(provider.models().withExistingParent(block.getName() + "_bottom", provider.modLoc("block/template_two_part_cross")).texture("stem", "block/" + block.getName() + "_stem_bottom").texture("leaves", "block/" + block.getName() + "_leaves_bottom")).build();
			} else {
				return ConfiguredModel.builder().modelFile(provider.models().withExistingParent(block.getName() + "_top", provider.modLoc("block/template_two_part_cross")).texture("stem", "block/" + block.getName() + "_branch_stem").texture("leaves", "block/" + block.getName() + "_branch_leaves")).build();
			}
		})).item().model((item, provider) -> provider.generated(() -> item.get(), provider.modLoc("block/" + name))).build();
	}

	public <T extends DoublePlantBlock> BlockBuilder<T, LostWorldsRegistrate> doublePlant(String name, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> provider.getVariantBuilder(block.get()).forAllStates(state -> {
			if (state.getValue(DoublePlantBlock.HALF) == DoubleBlockHalf.LOWER) {
				return ConfiguredModel.builder().modelFile(provider.models().cross(block.getName() + "_bottom", provider.modLoc("block/" + block.getName() + "_bottom"))).build();
			} else {
				return ConfiguredModel.builder().modelFile(provider.models().cross(block.getName() + "_top", provider.modLoc("block/" + block.getName() + "_top"))).build();

			}
		})).item().model((item, provider) -> provider.generated(() -> item.get(), provider.modLoc("block/" + name + "_top"))).build();
	}

	public <T extends DoublePlantBlock> BlockBuilder<T, LostWorldsRegistrate> cephalotaxus(String name, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> provider.getVariantBuilder(block.get()).forAllStates(state -> {
			if (state.getValue(DoublePlantBlock.HALF) == DoubleBlockHalf.LOWER) {
				return ConfiguredModel.builder().modelFile(provider.models().withExistingParent(block.getName() + "_bottom", provider.modLoc("block/dense_cross")).texture("plant", provider.modLoc("block/cephalotaxus_bottom"))).build();
			} else {
				return ConfiguredModel.builder().modelFile(provider.models().withExistingParent(block.getName() + "_top", provider.modLoc("block/dense_cross")).texture("plant", provider.modLoc("block/cephalotaxus_top"))).build();
			}
		})).loot((provider, block) -> provider.add(block, LootTable.lootTable().withPool(provider.withSurvivesExplosion(block, LootPool.lootPool().setRolls(ConstantRange.exactly(1)).add(ItemLootEntry.lootTableItem(block).when(BlockStateProperty.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)))))))).item().model((item, provider) -> provider.generated(() -> item.get(), provider.modLoc("block/" + name + "_top"))).color(() -> LostWorldsBlocks::getGrassyItemColour).build();
	}

	public <T extends Block> BlockBuilder<T, LostWorldsRegistrate> plant(String name, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> provider.getVariantBuilder(block.get()).partialState().setModels(new ConfiguredModel(provider.models().cross(block.getName(), new ResourceLocation(block.get().getRegistryName().getNamespace(), "block/" + block.getName()))))).item().model((item, provider) -> provider.generated(() -> item.get(), LostWorldsUtils.rL("block/" + name))).build();
	}

	public <T extends Block> BlockBuilder<T, LostWorldsRegistrate> parentName(String name, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> provider.getVariantBuilder(block.get()).partialState().addModels(new ConfiguredModel(provider.models().withExistingParent(block.getName() + "_gen", LostWorldsUtils.rL(block.getName()))))).item().color(() -> LostWorldsBlocks::getGrassyItemColour).build();
	}

	public <T extends Block> BlockBuilder<T, LostWorldsRegistrate> parentNameNoItem(String name, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> provider.getVariantBuilder(block.get()).partialState().addModels(new ConfiguredModel(provider.models().withExistingParent(block.getName() + "_gen", LostWorldsUtils.rL(block.getName())))));
	}

	public <T extends Block> BlockBuilder<T, LostWorldsRegistrate> pottedBlock(String name, String texture, NonNullFunction<Properties, T> factory) {
		if (texture != "archaefrutus" && texture != "calamites_suckowii") {
			return super.block(name, factory).blockstate((block, provider) -> provider.getVariantBuilder(block.get()).partialState().setModels(new ConfiguredModel(provider.models().singleTexture(block.getName(), new ResourceLocation("block/flower_pot_cross"), "plant", new ResourceLocation(block.get().getRegistryName().getNamespace(), "block/" + texture))))).tag(BlockTags.FLOWER_POTS);
		} else if (texture == "calamites_suckowii") {
			return super.block(name, factory).blockstate((block, provider) -> provider.getVariantBuilder(block.get()).partialState().setModels(new ConfiguredModel(provider.models().withExistingParent(block.getName(), new ResourceLocation("block/potted_bamboo")).texture("bamboo", LostWorldsUtils.rL("block/calamites_suckowii_stalk")).texture("leaf", LostWorldsUtils.rL("block/calamites_suckowii_singleleaf"))))).tag(BlockTags.FLOWER_POTS);
		} else {
			return super.block(name, factory).blockstate((block, provider) -> provider.getVariantBuilder(block.get()).partialState().setModels(new ConfiguredModel(provider.models().singleTexture(block.getName(), LostWorldsUtils.rL("block/water_flower_pot_cross"), "plant", new ResourceLocation(block.get().getRegistryName().getNamespace(), "block/" + texture))))).addLayer(() -> RenderType::translucent).color(() -> LostWorldsBlocks::getWaterColour).tag(BlockTags.FLOWER_POTS);
		}
	}

	public <T extends RotatedPillarBlock> BlockBuilder<T, LostWorldsRegistrate> rotatedBlock(String name, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> provider.logBlock(block.get())).tag(BlockTags.LOGS_THAT_BURN, BlockTags.LOGS).simpleItem();
	}

	public <T extends RotatedPillarBlock> BlockBuilder<T, LostWorldsRegistrate> rotatedWoodBlock(String name, String texture, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> provider.axisBlock(block.get(), provider.modLoc("block/" + texture), provider.modLoc("block/" + texture))).tag(BlockTags.LOGS_THAT_BURN, BlockTags.LOGS).simpleItem();
	}

	public <T extends StairsBlock> BlockBuilder<T, LostWorldsRegistrate> stairBlock(String name, String texture, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> provider.stairsBlock((StairsBlock) block.get(), provider.modLoc("block/" + texture))).simpleItem();
	}

	public <T extends SlabBlock> BlockBuilder<T, LostWorldsRegistrate> slabBlock(String name, String texture, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> provider.slabBlock((SlabBlock) block.get(), provider.modLoc("block/" + texture), provider.modLoc("block/" + texture))).simpleItem();
	}

	public <T extends WallBlock> BlockBuilder<T, LostWorldsRegistrate> wallBlock(String name, String texture, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> provider.wallBlock((WallBlock) block.get(), provider.modLoc("block/" + texture))).item().model(LostWorldsBlockModels.wallInv(texture)).build().tag(BlockTags.WALLS);
	}

	public <T extends FenceBlock> BlockBuilder<T, LostWorldsRegistrate> fenceBlock(String name, String texture, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> provider.fenceBlock((FenceBlock) block.get(), provider.modLoc("block/" + texture))).item().model(LostWorldsBlockModels.fenceInv(texture)).build();
	}

	public <T extends FenceGateBlock> BlockBuilder<T, LostWorldsRegistrate> fenceGateBlock(String name, String texture, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> provider.fenceGateBlock((FenceGateBlock) block.get(), provider.modLoc("block/" + texture))).tag(BlockTags.FENCE_GATES).simpleItem();
	}

	public <T extends AbstractButtonBlock> BlockBuilder<T, LostWorldsRegistrate> buttonBlock(String name, String texture, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> LostWorldsBlockModels.button(block.get(), texture, provider)).item().model((item, provider) -> LostWorldsBlockModels.buttonInv(item.get(), provider)).build();
	}

	public <T extends PressurePlateBlock> BlockBuilder<T, LostWorldsRegistrate> pressurePlateBlock(String name, String texture, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> LostWorldsBlockModels.pressurePlate(block.get(), texture, provider)).simpleItem();
	}

	public <T extends TrapDoorBlock> BlockBuilder<T, LostWorldsRegistrate> trapdoorBlock(String name, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> provider.trapdoorBlock((TrapDoorBlock) block.get(), provider.modLoc("block/" + name), true)).item().model((item, provider) -> provider.withExistingParent(name, provider.modLoc("block/" + name + "_bottom"))).build();
	}

	public <T extends DoorBlock> BlockBuilder<T, LostWorldsRegistrate> doorBlock(String name, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> provider.doorBlock((DoorBlock) block.get(), provider.modLoc("block/" + name + "_bottom"), provider.modLoc("block/" + name + "_top"))).loot((provider, block) -> provider.add(block, provider.createDoorTable(block))).item().model((item, provider) -> provider.generated(() -> item.get())).build();
	}

	public <T extends AbstractSignBlock> BlockBuilder<T, LostWorldsRegistrate> signBlock(String name, String texture, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> provider.getVariantBuilder(block.get()).partialState().addModels(new ConfiguredModel(provider.models().getBuilder(name).texture("particle", provider.modLoc("block/" + texture))))).lang(provider -> "block.lostworlds." + name + ".disabled", "Sign");
	}
}
