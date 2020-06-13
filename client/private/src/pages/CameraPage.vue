<template>

    
</template>

<script>
import WebCam from 'vue-web-cam';
import ml5 from 'ml5'
export default {
  name: 'cameras',
  components:{
      'web-cam':WebCam
  },
  data() {
    return {
      classifier: {},
      isModelLoaded: false,
      video:{},
      canvas:{}
    }
  },
  mounted: function() {
    WebCam.getCanvas

    this.classifier = ml5.imageClassifier('MobileNet', this.video)
  },
  methods: {
    capture() {
        this.canvas = this.$refs.canvas;
        var context = this.canvas.getContext("2d").drawImage(this.video, 0, 0, 640, 480);
        this.captures.push(canvas.toDataURL("image/png"));
    },
    classify: function() {
      // https://github.com/ml5js/ml5-library/blob/development/src/utils/IMAGENET_CLASSES.js
      this.classifier.classify(this.$refs.image, (err, results) => {
        if (err) {
          console.error(err)
        } else {
          console.log(results)
          this.result.label = results[0].label
          this.result.confidence = (results[0].confidence * 100).toFixed(2)
          this.getGiphy()
        }
      })
    },
    uploadImage: function(event) {
      const image = event.target.files[0]
      const reader = new FileReader()
      this.imgName = event.target.files[0].name
      reader.readAsDataURL(image)
      reader.onload = (e) => {
        this.img = e.target.result
        this.classify()
      }
    },
  }
}
</script>

