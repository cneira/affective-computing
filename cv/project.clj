(defproject cv "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"] [opencv/opencv "2.4.8"]
                 [opencv/opencv-native "2.4.8"]
                 [seesaw "1.4.4"]
                 ]
  :main ^:skip-aot cv.core
  :target-path "target/%s"
  :injections [(clojure.lang.RT/loadLibrary org.opencv.core.Core/NATIVE_LIBRARY_NAME)]
  :profiles {:uberjar {:aot :all}})
