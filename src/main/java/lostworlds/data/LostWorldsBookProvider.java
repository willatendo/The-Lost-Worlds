package lostworlds.data;

import java.util.function.Consumer;

import lostworlds.client.books.BookBuilder;
import lostworlds.client.books.BookBuilder.SectionBuilder;
import lostworlds.client.books.BookBuilder.SectionBuilder.PageBuilder;
import lostworlds.client.books.BookProvider;
import lostworlds.server.LostWorldsUtils;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Items;

public class LostWorldsBookProvider extends BookProvider {
	public LostWorldsBookProvider(DataGenerator generator) {
		super(generator, LostWorldsUtils.ID);
	}

	@Override
	public void makeBooks(Consumer<BookBuilder> consumer) {
		consumer.accept(new BookBuilder("field_guide", "Field Guide", "By Willatendo", 4546862).addSections(new SectionBuilder("creatures", Items.ACACIA_BOAT)
				.addPages(new PageBuilder("allosaurus", "text").addTitle("Allosaurus fragilis")
						.addFirst("Allosaurus, a large theropod from North America, commonly from the Morrison Formation. A massive apex preditor, Allosaurus would've feared little than starvation. First discovered during the Bone Wars, it was described in 1877.")
						.addNext("Allosaurus was 12 meters from nose to tail, around the size of an elephant. It would've eaten anything it could get its jaws arround. Sauropods, pachycephalosaurus, nothing was safe apart from the largest of Brachiosaurus."))
				.addPages(new PageBuilder("allosaurus_info", "text").addFirst("Danger: Timid").addNext("Diet: Carnivore").addNext("Range: Jurassic - 155-145 mya").addNext("Clade: Dinosauria").addNext("Clade: Saurischia").addNext("Clade: Theropoda").addNext("Family: Allosauridae").addNext("Subfamily: Allosaurinae").addNext("Genus: Allosaurus"))
				.addPages(new PageBuilder("anomalocaris", "text").addTitle("Anomalocaris canadensis")
						.addFirst("Anomalocaris, a large creature from formations across Canada, China, Greenland, Australia, and Utah. A large apex preditor, Anomalocaris ate the bottem dwelling trilobites of the Cambrian ocean. First discovered in 1886, it was described in 1892.")
						.addNext("Anomalocaris was 1 meters from head to tail, around the size of an cat. Trilobies and other bottom feeders were the Anomalocaris's main food source, using its large claw-like arms."))
				.addPages(new PageBuilder("anomalocaris_info", "text").addFirst("Danger: Agressive").addNext("Diet: Carnivore").addNext("Range: Cambrian - 520-499 mya").addNext("Class: Dinocaridida").addNext("Order: Radiodonta").addNext("Family: Anomalocarididae").addNext("Genus: Anomalocaris"))
				.addPages(new PageBuilder("carnotaurus", "text").addTitle("Carnotaurus sastrei")
						.addFirst("Carnotaurus, a medium theropod from South America, found in the La Colonia Formation. A medium preditor, Carnotaurus would've feared other Carnotaurus and particularly feisty prey. First discovered in 1984, it was described in 1985.")
						.addNext("Carnotaurus was 8 meters from nose to tail and would've presued anything it could get its jaws arround."))
				.addPages(new PageBuilder("carnotaurus_info", "text").addFirst("Danger: Timid").addNext("Diet: Carnivore").addNext("Range: Cretaceous - 71 mya").addNext("Clade: Dinosauria").addNext("Clade: Saurischia").addNext("Clade: Theropoda").addNext("Family: Abelisauridae").addNext("Clade: Furileusauria").addNext("Tribe: Carnotaurini").addNext("Genus: Carnotaurus"))
				.addPages(new PageBuilder("chilesaurus", "text").addTitle("Chilesaurus diegosuarezi")
						.addFirst("Chilesaurus, a small ornithoscelida from Chile in the Uper Jurassic Toqui Formation. A mix of many taxonomy, including that of theropods, ornithischians, and sauropodomorphs, this little dinosaur is and oddity in paleontology. First confirmed discovery in 2004, described in 2015, the Chilesaurus has been place in many taxonomy.")
						.addNext("Chilesaurus was 3.2 meters from nose to tail, around the size of a dog. It would've eaten smaller plants along the ground, though the teeth are that of a sterotypical theropod."))
				.addPages(new PageBuilder("chilesaurus_info", "text").addFirst("Danger: Passive").addNext("Diet: Herbivore").addNext("Range: Jurassic - 165 mya").addNext("Clade: Dinosauria").addNext("Genus: Chilesaurus"))
				.addPages(new PageBuilder("cryolophosaurus", "text").addTitle("Cryolophosaurus ellioti")
						.addFirst("Cryolophosaurus, a medium theropod from Antarctica, from the Hanson Formation. A medium preditor, Cryolophosaurus would've feared other Cryolophosaurus and particularly feisty prey. First discovered in 1991, it was described in 1993.")
						.addNext("Cryolophosaurus was 6 meters from nose to tail and would've presued anything it could get its jaws arround and would've been covered in feathers to combat the cold."))
				.addPages(new PageBuilder("cryolophosaurus_info", "text").addFirst("Danger: Timid").addNext("Diet: Carnivore").addNext("Range: Jurassic - 190-186 mya").addNext("Clade: Dinosauria").addNext("Clade: Eusaurischia").addNext("Clade: Theropoda").addNext("Clade: Neotheropoda").addNext("Genus: Cryolophosaurus"))
				.addPages(new PageBuilder("diictodon", "text").addTitle("Diictodon feliceps")
						.addFirst("Diictodon, a tiny synapsid from Zambia, South Africa, and China. A tiny burrower, Diictodon would've feared predators like the gorgonops. First discovered in 1991, it was described in 1993.")
						.addNext("Diictodon was 50 centimeters from nose to tail and would've spent its life in its burrows."))
				.addPages(new PageBuilder("diictodon_info", "text").addFirst("Danger: None").addNext("Diet: Herbivore").addNext("Range: Permian - 259.8-254.1 mya").addNext("Clade: Synapsida").addNext("Clade: Therapsida").addNext("Clade: Dicynodontia").addNext("Family: Pylaecephalidae").addNext("Genus: Diictodon"))
				.addPages(new PageBuilder("dilophosaurus", "text").addTitle("Dilophosaurus wetherilli")
						.addFirst("Dilophosaurus, a large theropod from North America. This large dinosaur is a fierce creature that would've been an early top predator. First confirmed discovery in 1940, described in 1954, the Dilophosaurus got a face-lift in early 2020, with a more correct frill.")
						.addNext("Dilophosaurus was 7 meters from nose to tail, around the size of a rhino. It would've hunted down large game, and would be a mighty fow, thought it was solitary."))
				.addPages(new PageBuilder("dilophosaurus_info", "text").addFirst("Danger: Aggressive").addNext("Diet: Carnivore").addNext("Range: Jurassic - 193 mya").addNext("Clade: Dinosauria").addNext("Clade: Saurischia").addNext("Clade: Theropoda").addNext("Genus: Dilophosaurus"))));
	}
}








































































