
import groovy.util.logging.Log
import marytts.util.data.audio.MaryAudioUtils

import javax.sound.sampled.AudioSystem


@Log
class PoC {
    def samples
    def actualArray

    PoC(File file) {
        log.warning "$this should be loading $file"
    }

    double[] getSamples() {
        log.warning "$this should be decoding the samples!"

    }

}

class Main{
    def static tempDir = new File(System.getProperty('user.dir'))
    public static void main(String[] args){
        def flac = new File("$tempDir/src/test/resources/sample1.flac")
        PoC poc = new PoC(flac)

    }
}
