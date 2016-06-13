import org.testng.annotations.*
import marytts.util.data.audio.MaryAudioUtils
import javax.sound.sampled.AudioSystem

class PoCTest {

    def tempDir

    @BeforeClass
    void setup() {
        tempDir = new File(System.getProperty('tempDir'))
    }

    @Test
    void testDecoding() {
        def expectedFile = new File("$tempDir/expected.wav")
        def expectedAIS = AudioSystem.getAudioInputStream(expectedFile)
        def expected = MaryAudioUtils.getSamplesAsDoubleArray(expectedAIS)
        def poc = new PoC(expectedFile)
        def actual = poc.samples
        assert expected == actual
    }

    @Test
    void testDecodingExternal() {
        def expectedFile = new File("$tempDir/expected.wav")
        def expectedAIS = AudioSystem.getAudioInputStream(expectedFile)
        def expected = MaryAudioUtils.getSamplesAsDoubleArray(expectedAIS)
        def proc = 'sox test.flac actual.wav'.execute(null, tempDir)
        proc.waitFor()
        def actualFile = new File("$tempDir/actual.wav")
        assert actualFile.exists()
        def actualAIS = AudioSystem.getAudioInputStream(actualFile)
        def actual = MaryAudioUtils.getSamplesAsDoubleArray(actualAIS)
        assert expected == actual
    }
}
