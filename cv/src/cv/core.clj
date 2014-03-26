(ns cv.core
  (:gen-class)
(:import
    org.opencv.core.Core
    org.opencv.core.Mat
    org.opencv.core.MatOfRect
    org.opencv.core.Point
    org.opencv.core.Rect
    org.opencv.core.Scalar
    org.opencv.highgui.Highgui
    org.opencv.objdetect.CascadeClassifier
    org.opencv.highgui.VideoCapture
    )
  )



(def face-detections (atom []))

(defn create-classifier
  []
  (CascadeClassifier.
   "C:/Users/a03550a/Documents/BD/clojure/cv/resources/lbpcascade_frontalface.xml"))

(defn load-image
  []
  (Highgui/imread "C:/Users/a03550a/Documents/BD/clojure/cv/resources/lena.png"))


(defn detect-faces!
  [classifier image]
  (.detectMultiScale classifier image @face-detections))

(defn draw-bounding-boxes!
  [image]
  (doall (map (fn [rect]
                (Core/rectangle image
                        (Point. (.x rect) (.y rect))
                        (Point. (+ (.x rect) (.width rect))
                                (+ (.y rect) (.height rect)))
                        (Scalar. 0 255 0)))
              (.toArray @face-detections)))
  (Highgui/imwrite "faceDetections.png" image))


(defn load-camera []
  (let [ [f v ] [ (new Mat  )   (new VideoCapture 0 )] ]
    ( Thread/sleep 1000)
    (.open v 0 )
    (.read  v  f )
    (Highgui/imwrite "resources/came1.png" f  )
    (.release v)
    )
  )



(defn process-and-save-image!
  []
  (let [image (load-image)]
    (detect-faces! (create-classifier) image)
    (draw-bounding-boxes! image)))

 ;; (defn -main
 ;;  []
 ;;  (clojure.lang.RT/loadLibrary Core/NATIVE_LIBRARY_NAME)
 ;;  (reset! face-detections (MatOfRect.))
 ;;  (process-and-save-image!))


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (clojure.lang.RT/loadLibrary Core/NATIVE_LIBRARY_NAME)
  (reset! face-detections (MatOfRect.))
  (process-and-save-image!)
 (load-camera)


  )
