package lostworlds.data;

import java.util.function.Consumer;

import lostworlds.client.books.BookBuilder;
import lostworlds.client.books.BookBuilder.SectionBuilder;
import lostworlds.client.books.BookBuilder.SectionBuilder.PageBuilder.TextPageBuilder;
import lostworlds.client.books.BookProvider;
import lostworlds.server.LostWorldsUtils;
import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.item.LostWorldsItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Items;

public class LostWorldsBookProvider extends BookProvider {
	public LostWorldsBookProvider(DataGenerator generator) {
		super(generator, LostWorldsUtils.ID);
	}

	@Override
	public void makeBooks(Consumer<BookBuilder> consumer) {
		consumer.accept(new BookBuilder("field_guide", "Field Guide", "By Willatendo", 4546862).addSections(new SectionBuilder("creatures", Items.ACACIA_BOAT)
				/* Allosaurus */
				.addPages(new TextPageBuilder("allosaurus_1").addTitle("Allosaurus fragilis")
				.addFirst("Allosaurus was a large theropod from North America. It was most commonly found in the Morrison Formation. A massive apex predator, Allosaurus would've feared little than starvation and strong competition. The first specimens were discovered during the Bone Wars though, it was described in 1877.")
				.addNext("Allosaurus was 12 meters from nose to tail. Allosaurus would've eaten anything it could get its jaws arround. Sauropods, pachycephalosaurus, nothing was safe apart from the largest of"))
				.addPages(new TextPageBuilder("allosaurus_2").addFirst("Brachiosaurus. Allosaurus was thought to have hunted in packs to take down larger foe, making this theropod one of the scariest of creatures")
				.addNext("One of the most complete and well known specimens, Big Al, has taught us a lot about Allosaurus's rugged life style. Big Al had sustained many injuries throught their relatively short 7-year life.")
				.addNext("Some of Allosaurus's rivals were thought to be Ceratosaurus and Torvosaurus. Despite the generally weak bite force, Allosaurus could've inflicted very heavy bleeding to"))
				.addPages(new TextPageBuilder("allosaurus_3").addFirst("anything caught between its jaws.").addNext("Should you find yourself face-to-face with a hungry or angry member of Allosaurus, you'd better run, fast."))
				.addPages(new TextPageBuilder("allosaurus_info").addFirst("Danger: Large (When Threatened)").addNext("Diet: Carnivore").addNext("Range: Jurassic - 155-145 mya")
				.addNext("Clade: Dinosauria").addNext("Clade: Saurischia").addNext("Clade: Theropoda").addNext("Family: Allosauridae").addNext("Subfamily: Allosaurinae").addNext("Genus: Allosaurus"))
				/* Anomalocaris */
				.addPages(new TextPageBuilder("anomalocaris_1").addTitle("Anomalocaris canadensis")
				.addFirst("Anomalocaris, a large cambrian apex predator. Found in formations across Canada, China, Greenland, Australia, and Utah. Anomalocaris ate the bottem dwelling trilobites of the Cambrian ocean. Should you find yourself infront of one, you'll find no danger.")
				.addNext("Anomalocaris was first discovered in 1886, it was described in 1892.")
				.addNext("Anomalocaris was 2 meters from head to tail. Trilobies and other bottom feeders were the Anomalocaris's"))
				.addPages(new TextPageBuilder("anomalocaris_2").addFirst("main food source. It's large mandibles could crack a trilobite in half before consumption. Using large, compact eyes atop of it's head, Anomalocaris scaned the ocean floor for food, with large, theorized, gill-like structures to each side."))
				.addPages(new TextPageBuilder("anomalocaris_info").addFirst("Danger: Agressive").addNext("Diet: Carnivore").addNext("Range: Cambrian - 520-499 mya").addNext("Class: Dinocaridida").addNext("Order: Radiodonta").addNext("Family: Anomalocarididae").addNext("Genus: Anomalocaris"))
				.addPages(new TextPageBuilder("anomalocaris_padding"))
				/* Carnotaurus */
				.addPages(new TextPageBuilder("carnotaurus").addTitle("Carnotaurus sastrei")
						.addFirst("Carnotaurus, a medium theropod from South America, found in the La Colonia Formation. A medium preditor, Carnotaurus would've feared other Carnotaurus and particularly feisty prey. First discovered in 1984, it was described in 1985.").addNext("Carnotaurus was 8 meters from nose to tail and would've presued anything it could get its jaws arround.")).addPages(new TextPageBuilder("carnotaurus_info").addFirst("Danger: Timid").addNext("Diet: Carnivore").addNext("Range: Cretaceous - 71 mya").addNext("Clade: Dinosauria").addNext("Clade: Saurischia").addNext("Clade: Theropoda").addNext("Family: Abelisauridae").addNext("Clade: Furileusauria").addNext("Tribe: Carnotaurini").addNext("Genus: Carnotaurus")).addPages(new TextPageBuilder("chilesaurus").addTitle("Chilesaurus diegosuarezi").addFirst("Chilesaurus, a small ornithoscelida from Chile in the Uper Jurassic Toqui Formation. A mix of many taxonomy, including that of theropods, ornithischians, and sauropodomorphs, this little dinosaur is and oddity in paleontology. First confirmed discovery in 2004, described in 2015, the Chilesaurus has been place in many taxonomy.").addNext("Chilesaurus was 3.2 meters from nose to tail, around the size of a dog. It would've eaten smaller plants along the ground, though the teeth are that of a sterotypical theropod.")).addPages(new TextPageBuilder("chilesaurus_info").addFirst("Danger: Passive").addNext("Diet: Herbivore").addNext("Range: Jurassic - 165 mya").addNext("Clade: Dinosauria").addNext("Genus: Chilesaurus")).addPages(new TextPageBuilder("cryolophosaurus").addTitle("Cryolophosaurus ellioti").addFirst("Cryolophosaurus, a medium theropod from Antarctica, from the Hanson Formation. A medium preditor, Cryolophosaurus would've feared other Cryolophosaurus and particularly feisty prey. First discovered in 1991, it was described in 1993.").addNext("Cryolophosaurus was 6 meters from nose to tail and would've presued anything it could get its jaws arround and would've been covered in feathers to combat the cold.")).addPages(new TextPageBuilder("cryolophosaurus_info").addFirst("Danger: Timid").addNext("Diet: Carnivore").addNext("Range: Jurassic - 190-186 mya").addNext("Clade: Dinosauria").addNext("Clade: Eusaurischia").addNext("Clade: Theropoda").addNext("Clade: Neotheropoda").addNext("Genus: Cryolophosaurus")).addPages(new TextPageBuilder("diictodon").addTitle("Diictodon feliceps").addFirst("Diictodon, a tiny synapsid from Zambia, South Africa, and China. A tiny burrower, Diictodon would've feared predators like the gorgonops. First discovered in 1991, it was described in 1993.").addNext("Diictodon was 50 centimeters from nose to tail and would've spent its life in its burrows.")).addPages(new TextPageBuilder("diictodon_info").addFirst("Danger: None").addNext("Diet: Herbivore").addNext("Range: Permian - 259.8-254.1 mya").addNext("Clade: Synapsida").addNext("Clade: Therapsida").addNext("Clade: Dicynodontia").addNext("Family: Pylaecephalidae").addNext("Genus: Diictodon")).addPages(new TextPageBuilder("dilophosaurus").addTitle("Dilophosaurus wetherilli").addFirst("Dilophosaurus, a large theropod from North America. This large dinosaur is a fierce creature that would've been an early top predator. First confirmed discovery in 1940, described in 1954, the Dilophosaurus got a face-lift in early 2020, with a more correct frill.").addNext("Dilophosaurus was 7 meters from nose to tail, around the size of a rhino. It would've hunted down large game, and would be a mighty fow, thought it was solitary.")).addPages(new TextPageBuilder("dilophosaurus_info").addFirst("Danger: Aggressive").addNext("Diet: Carnivore").addNext("Range: Jurassic - 193 mya").addNext("Clade: Dinosauria").addNext("Clade: Saurischia").addNext("Clade: Theropoda").addNext("Genus: Dilophosaurus")).addPages(new TextPageBuilder("dimetrodon").addTitle("Dimetrodon limbatus").addFirst("Dimetrodon, a large synapsid from the United States. A large, apex predator, Dimetrodon would't've feared much. Filling many niches as it grew, in adulthood, it would've eaten most things. It was described in the 1870s.").addNext("Dimetrodon was 4 meters from nose to tail. Eating anything that it could catch.")).addPages(new TextPageBuilder("dimetrodon_info").addFirst("Danger: Timid").addNext("Diet: Carnivore").addNext("Range: Permian - 295-272 mya").addNext("Clade: Synapsida").addNext("Family: Sphenacodontidae").addNext("Subfamily: Sphenacodontinae").addNext("Genus: Dimetrodon")).addPages(new TextPageBuilder("edaphosaurus").addTitle("Edaphosaurus limbatus").addFirst("Edaphosaurus, a medium synapsid from the North America and Europe. A medium herbivore, Edaphosaurus was discovered in described in 1912 and descrbed in 1931.").addNext("Edaphosaurus was 3 meters from nose to tail. Eating small plants and shrubs.")).addPages(new TextPageBuilder("edaphosaurus_info").addFirst("Danger: Neutral").addNext("Diet: Herbivore").addNext("Range: Carboniferous - Permian - 303.4-272.5 mya").addNext("Clade: Synapsida").addNext("Clade: Sphenacomorpha").addNext("Family: Edaphosauridae").addNext("Genus: Edaphosaurus")).addPages(new TextPageBuilder("eoraptor").addTitle("Eoraptor lunensis").addFirst("Eoraptor, a tiny sauropodomorph from trassic period South America. Specificly, the Ischigualasto Formation. This tiny dinosaur is a small creature that picks on only the tiniest of dinosaurs. First discovered in 1991, it was described in 1993.").addNext("Eoraptor was 1.5 meters from nose to tail, around the size of a small dog. It would've hunted down small game and ate some plants on rare occasion")).addPages(new TextPageBuilder("eoraptor_info").addFirst("Danger: Timid").addNext("Diet: Omnivore").addNext("Range: Triassic - 231-228 mya").addNext("Clade: Dinosauria").addNext("Clade: Saurischia").addNext("Clade: Sauropodomorpha").addNext("Genus: Eoraptor")).addPages(new TextPageBuilder("fukuivenator").addTitle("Fukuivenator paradoxus").addFirst("Fukuivenator, a small theropod from cretaceous period Japan. From the the Kitadani Formation. This is a small theropod that picked on small prey. First discovered and described in 2016.").addNext("Fukuivenator was 2.45 meters from nose to tail, around the size of a medium dog or cat. It would've hunted down smaller game.")).addPages(new TextPageBuilder("fukuivenator_info").addFirst("Danger: Small").addNext("Diet: Carnivore").addNext("Range: Cretaceous - 127-115 mya").addNext("Clade: Dinosauria").addNext("Clade: Saurischia").addNext("Clade: Theropoda").addNext("Clade: Maniraptoriformes").addNext("Clade: Maniraptora").addNext("Genus: Fukuivenator")).addPages(new TextPageBuilder("giganotosaurus").addTitle("Giganotosaurus carolinii").addFirst("Giganotosaurus, a massive theropod from the cretaceous period. From the Candeleros Formation in Argentina. This massive species had no natural predators except maybe other giganotosaurus and starvation. First discovered in 1993 and described in 1995.").addNext("Giganotosaurus was 12 meters from nose to tail, around the size of multiple elephants. It would've hunted down anything it's jaws could fit around. Even titanosaurs.")).addPages(new TextPageBuilder("giganotosaurus_info").addFirst("Danger: Massive").addNext("Diet: Carnivore").addNext("Range: Cretaceous - 127-115 mya").addNext("Clade: Dinosauria").addNext("Clade: Saurischia").addNext("Clade: Theropoda").addNext("Family: Carcharodontosauridae").addNext("Tribe: Giganotosaurini").addNext("Genus: Giganotosaurus")).addPages(new TextPageBuilder("gorgonops").addTitle("Gorgonops torvus").addFirst("Gorgonops, a medium therapsid from the permian period from South Africa. This average gorgonopsid had little predators other than larger gorgonopsids that shared it's habitat and starvation. First discovered in 1876 and described in 1890.").addNext("Gorgonops was 2 meters from nose to tail, about the size of a cat. It would've hunted smaller animals and may have had to defend itself agenst larger gorgonopsids.")).addPages(new TextPageBuilder("gorgonops_info").addFirst("Danger: Small").addNext("Diet: Carnivore").addNext("Range: Permian - 260-254 mya").addNext("Clade: Synapsida").addNext("Clade: Therapsida").addNext("Clade: Gorgonopsia").addNext("Family: Gorgonopsidae").addNext("Subfamily: Gorgonopsinae").addNext("Genus: Gorgonops")).addPages(new TextPageBuilder("greak_auk").addTitle("Pinguinus impennis - Great Auk").addFirst("Great Auk, a large auk from the Neogene to Holocene. This large auk spaned across the North Pacific ocean. It had the odd predators like Polar Bears and Humans.").addNext("The Great Auk was 80 centimeters tall. It would've hunted fish. Humans traded thier parts all across North America and Europe.")).addPages(new TextPageBuilder("greak_auk_info").addFirst("Danger: Minimal").addNext("Diet: Piscivore").addNext("Range: Neogene - Late Holocene").addNext("Order: Charadriiformes").addNext("Family: Alcidae").addNext("Genus: Pinguinus")).addPages(new TextPageBuilder("kentrosaurus").addTitle("Kentrosaurus aethiopicus").addFirst("Kentrosaurus, a medium stegosaurid from Tanzania from the Tendaguru Formation. From stegosauridae, it's oftan thought to be a primitive stegosaur, but that is not the case, being closer related to Stegosaurus from the Morrison Formation. Discovered in 1909, described in 1915, it was a victim of WWII, with a lot of skeletons being destroyed.").addNext("Kentrosaurus was 4.6 meters from nose to tail, around the size of a large tigon. It would've been a grazer much like its North American cousin, Stegosaurus.")).addPages(new TextPageBuilder("kentrosaurus_info").addFirst("Danger: Timid").addNext("Diet: Herbivore").addNext("Range: Jurassic - 152 mya").addNext("Clade: Dinosauria").addNext("Order: Ornithischia").addNext("Suborder: Stegosauria").addNext("Family: Stegosauridae").addNext("Genus: Kentrosaurus")).addPages(new TextPageBuilder("liaoningosaurus").addTitle("Liaoningosaurus paradoxus").addFirst("Liaoningosaurus, a small ankyolosaur from the Cretaceous. This small ankyolosaur is from the Yixian Formation in China. It wouldn't fear too much other than the larger carnivore that could break through it's strong back.").addNext("The only studied Liaoningosaurus fossil was 34 centimeters, but proven to only be 1 year old. It would've lived a standard lifestyle, grazing on small plants")).addPages(new TextPageBuilder("liaoningosaurus_info").addFirst("Danger: None").addNext("Diet: Herbivore").addNext("Range: Cretaceous - 122 mya").addNext("Clade: Dinosauria").addNext("Order: Ornithischia").addNext("Suborder: Ankylosauria").addNext("Clade: Euankylosauria").addNext("Family: Ankylosauridae").addNext("Genus: Liaoningosaurus"))));
		consumer.accept(new BookBuilder("lost_worlds_lexicon", "Lexicon", "By Willatendo", 3304264).addSections(new SectionBuilder("introduction", LostWorldsItems.LEATHER_BRUSH.get()).addPages(new TextPageBuilder("introduction").addTitle("Welcome to The Lost Worlds!").addFirst("A Minecraft dinosaur mod!").addNext("This Lexicon will tell you everything you need to know about the mod, from creating creatures to exploring ancient time eras.").addNext("The Lexicon is split into 4 major parts, with each provides information on a particular branch of the mod. They are organised in order of importance of information.")).addPages(new TextPageBuilder("note").addFirst("This book and mod are still not done! To ensure the quality and make everything the best it can be, we are a bit slow and want to ensure that everything is as quality and perfect as possible."))).addSections(new SectionBuilder("world", LostWorldsBlocks.JURASSIC_COBBLESTONE.get())).addSections(new SectionBuilder("creature_creation", LostWorldsBlocks.CULTIVATOR.get())).addSections(new SectionBuilder("creature_care", Items.WHEAT)).addSections(new SectionBuilder("time_traveling", LostWorldsItems.JURASSIC_PERIOD_TIME_BOOK.get())));
	}
}
