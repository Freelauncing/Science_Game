package com.sciencegame.data

import com.sciencegame.R

data class Guide(
    val text: String,
    val imageResourceId: Int)

data class Question(
    val text: String,
    val answers: List<String>)

class SampleData {
    companion object {
        val guides: MutableList<Guide> = mutableListOf(
             Guide(
                "The life cycle for a particular star depends on its size. The diagram shows the life cycles of stars that are:\n" +
                        "\n" +
                        "->about the same size as the Sun\n" +
                        "->far greater than the Sun in size \n\nAll stars begin life in the same way. A cloud of dust and gas, also known as a nebula, becomes a protostar, which goes on to become a main sequence star. Following this, stars develop in different ways depending on their size.\n" +
                        "\n" +
                        "Stars that are a similar size to the Sun follow the left hand path:\n" +
                        "\n" +
                        "red giant star --> white dwarf -->black dwarf\n" +
                        "\n" +
                        "Stars that are far greater in mass than the Sun follow the right hand path:\n" +
                        "\n" +
                        "red super giant star --> supernova --> neutron star, or a black hole (depending on size)",
                R.drawable.img_chart
            ),
             Guide(
                "A nebula\n\n" +
                        "A star forms from massive clouds of dust and gas in space, also known as a nebula. Nebulae are mostly composed of hydrogen.\n" +
                        "", R.drawable.img_nebula_1
            ),
             Guide(
                "Gravity begins to pull the dust and gas together.\n",
                R.drawable.img_nebula
            ),
             Guide(
                "Protostar\n\n" +
                        "As the mass falls together it gets hot. A star is formed when it is hot enough for the hydrogen nuclei to fuse together to make helium. The fusion process releases energy, which keeps the core of the star hot.",
                R.drawable.img_protostar
            ),
             Guide(
                "Main sequence star\n\n" +
                        "During this stable phase in the life of a star, the force of gravity holding the star together is balanced by higher pressure due to the high temperatures. The Sun is at this stable phase in its life.\n" +
                        "\n", R.drawable.img_mainsequence
            ),
             Guide(
                "Red giant star\n\n" +
                        "When all the hydrogen has been used up in the fusion process, larger nuclei begin to form and the star may expand to become a red giant.",
                R.drawable.img_redgaint
            ),
             Guide(
                "White dwarf\n\n" +
                        "When all the nuclear reactions are over, a small star like the Sun may begin to contract under the pull of gravity. In this instance, the star becomes a white dwarf which fades and changes colour as it cools.",
                R.drawable.img_whitedwarf
            ),
             Guide(
                "Supernova\n\n" +
                        "A larger star with more mass will go on making nuclear reactions, getting hotter and expanding until it explodes as a supernova.",
                R.drawable.img_supernove
            ),

             Guide(
                "An exploding supernova throws hot gas into space.",
                R.drawable.img_supernove_explode
            ),

             Guide(
                "Neutron star or black hole\n\n" +
                        "Depending on the mass at the start of its life, a supernova will leave behind either a neutron star or a black hole.",
                R.drawable.img_neutron
            )

            ,  Guide(
                "Main sequence stars\n\n" +
                        "For most of its lifetime, a star is a main sequence star. It is stable, with balanced forces keeping it the same size all the time. During this period:\n" +
                        "\n" +
                        "-> gravitational attraction tends to collapse the star\n" +
                        "-> radiation pressure from the fusion reactions tends to expand the star\n" +
                        "-> forces caused by gravitational attraction and fusion energy are balanced\n" +
                        "The Sun is expected to be a main sequence star for billions of years.\n\n" +
                        "\n" +
                        "Fusion reactions\n\n" +
                        "In a main sequence star, hydrogen nuclei fuse together to form helium nuclei. This happens in several steps, but one way to simplify the overall change is:\n" +
                        "\n" +
                        "\n" +
                        "Two hydrogen nuclei fuse to produce a helium nucleus and a neutron.",
                R.drawable.img_equation
            )
        )
    
        // the first one option is correct in here but it will change when we use
        val questions: MutableList<Question> = mutableListOf(
            Question(
                text = "A nebula consists primarily of which element?",
                answers = listOf("Hydrogen", "Helium", "Nitrogen", "Oxygen")
            ),
    
            Question(
                text = "Which sequence below is correct?",
                answers = listOf(
                    "Nebula, Protostar, Main Sequence, Red Giant",
                    "Red Giant, Nebula, Main Sequence, Protostar",
                    "Nebula, Main Sequence, Protostar, Red Giant",
                    "Red Giant, Nebula, Protostar, Main Sequence"
                )
            ),
    
            Question(
                text = "At which stage does a star swell up many times larger than its normal size?",
                answers = listOf("Red Giant", "Protostar", "Main Sequence", "Nebula")
            ),
    
            Question(
                text = "The eventual type of star death depends on?",
                answers = listOf("Mass", "Color", "Shape", "Composition")
            ),
    
            Question(
                text = "Less massive stars turn into ___ when they die?",
                answers = listOf(
                    "White Dwarfs",
                    "Black Hole",
                    "Neutron Star",
                    "Either a black hole or a neutron star"
                )
            ),
    
            Question(
                text = "More massive stars turn into ___ when they die?",
                answers = listOf(
                    "Either a black hole or a neutron star",
                    "White Dwarf",
                    "Black Hole",
                    "Neutron Star"
                )
            ),
    
            Question(
                text = "Which stage in a stars life is similar to old age in humans?",
                answers = listOf("White Dwarf", "Red Giant", "Main Sequence", "Nebula")
            ),
    
            Question(
                text = "Which stage in a stars life is similar to a humans 20's and 30's?",
                answers = listOf("Main Sequence", "Nebula", "Protostar", "Red Giant")
            ),
    
            Question(
                text = "Which star color is the hottest?",
                answers = listOf("Blue", "Red", "Yellow", "White")
            ),
    
            Question(
                text = "What are stars classified by?",
                answers = listOf("Spectra", "Mass", "Temperature", "Luminosity")
            ),
    
            Question(
                text = "What determines the age of a star?",
                answers = listOf("Spectra", "Mass", "Temperature", "Luminosity")
            ),
    
            Question(
                text = "The point in a star's evolution during which it maintains a stable nuclear reaction is?",
                answers = listOf("Main Sequence", "Protostar", "White Dwarf", "Supernova")
            ),
    
            Question(
                text = "What is the correct order of the spectral classes from hottest to coldest?",
                answers = listOf("OBAFGKM", "MKGFABO", "OFABGKM", "FBAKOMG")
            )
        )
    }
}