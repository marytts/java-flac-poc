import groovy.util.logging.Log
import org.kc7bfi.jflac.FLACDecoder
import org.kc7bfi.jflac.PCMProcessor
import org.kc7bfi.jflac.metadata.StreamInfo
import org.kc7bfi.jflac.util.ByteData
import org.kc7bfi.jflac.util.WavWriter

import java.util.logging.Level

@Log
class PoC implements PCMProcessor{
    def samples
    def inputStream
    def outputStream
    def wav

    PoC(File inputFile) {
        log.info("Loading FLAC file")
        this.inputStream = new FileInputStream(inputFile)
        log.warning "$this should be loading $file"
    }

    def decode(File outputFile){
        log.info("Setting up decoder")
        this.outputStream = new FileOutputStream(outputFile)
        this.wav = new WavWriter(outputStream)
        def decoder = new FLACDecoder(this.inputStream)
        decoder.addPCMProcessor(this)
        decoder.decode()
    }

    double[] getSamples() {
        log.warning "$this should be decoding the samples!"

    }

    @Override
    void processStreamInfo(StreamInfo streamInfo) {
        log.info("Writing Stream Information")
        this.wav.writHeader(streamInfo)

    }

    @Override
    void processPCM(ByteData pcm) {
        log.info("Adding PCM")
        this.wav.addPCM(pcm)
    }
}

class Main{
    def static tempDir = new File(System.getProperty('user.dir'))
    public static void main(String[] args){
        def flac = new File("$tempDir/src/test/resources/sample1.flac")
        def wav = new File("$tempDir/src/test/resources/sample1_out.wav")
        PoC poc = new PoC(flac)
        poc.decode(wav)

    }
}
