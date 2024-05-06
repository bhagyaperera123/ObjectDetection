import ai.djl.Application
import ai.djl.modality.cv.Image
import ai.djl.modality.cv.ImageFactory
import ai.djl.modality.cv.output.DetectedObjects
import ai.djl.repository.zoo.Criteria
import ai.djl.repository.zoo.ModelZoo
import ai.djl.training.util.ProgressBar
import java.nio.file.Files
import java.nio.file.Paths
import javax.imageio.ImageIO

fun main() {
    val criteria = Criteria.builder()
        .optApplication(Application.CV.OBJECT_DETECTION)
        .setTypes(Image::class.java, DetectedObjects::class.java)
        .optFilter("backbone", "resnet50")
        .optProgress(ProgressBar())
        .build()

    val imageFactory = ImageFactory.getInstance()
    val imagePath = Paths.get("images/cat.jpg")
    val bufferedImage = ImageIO.read(imagePath.toFile()) // Load image as BufferedImage
    val image = imageFactory.fromImage(bufferedImage) // Convert BufferedImage to DJL Image

    // load model
    val model = ModelZoo.loadModel(criteria)
    val predictor = model.newPredictor()
    val detectedObject = predictor.predict(image)
    model.close()

    println(detectedObject)

    image.drawBoundingBoxes(detectedObject)

    val resultPath = Paths.get("results/result.png")

    image.save(Files.newOutputStream(resultPath), "png")
}
