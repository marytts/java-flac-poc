import org.codehaus.groovy.runtime.powerassert.PowerAssertionError
import org.testng.annotations.*
import marytts.util.data.audio.MaryAudioUtils
import javax.sound.sampled.AudioSystem

class PoCTest {

    def tempDir

    @BeforeClass
    void setup() {
        tempDir = new File(System.getProperty('tempDir'))
    }

    @Test//(expectedExceptions = PowerAssertionError.class)
    void testDecoding() {
        def expectedFile = new File("$tempDir/expected.wav")
        def expectedAIS = AudioSystem.getAudioInputStream(expectedFile)
        def expected = MaryAudioUtils.getSamplesAsDoubleArray(expectedAIS)
        def actualFile = new File("$tempDir/actual.wav")
        def inputFile = new File("$tempDir/test.flac")
        def poc = new PoC(inputFile)
        poc.decode(actualFile)
        assert actualFile.exists()
        def actual = poc.getSamples(actualFile)
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
