<template>
  <q-page class="flex flex-center">
    <div class="flex flex-center">
     <q-card class="original" style="min-width: 350px">
        <q-card-section class="bg-primary text-white" style="min-height: 350px">

          <q-input outlined @input="translate('title')" v-model="titleO" placeholder="Titulo" label="Outlined" />
          <div class="q-pa-md q-gutter-sm">
            <q-editor
              @input="translate('content')"
              v-model="contentO"
              flat
              content-class="bg-black"
              toolbar-text-color="white"
              toolbar-toggle-color="black-8"
              toolbar-bg="green"
              :toolbar="[
                ['bold', 'italic', 'underline'],
                [{
                  label: $q.lang.editor.formatting,
                  icon: $q.iconSet.editor.formatting,
                  list: 'no-icons',
                  options: ['p', 'h3', 'h4', 'h5', 'h6', 'code']
                }]
              ]"/>
          </div>

        </q-card-section>

        <q-separator />

        <q-card-actions align="right">
        <q-select style="min-width: 200px"
          v-model="origCode"
          :options="languages"
          label="Original languange"/>
        </q-card-actions>
      </q-card>

        <div v-if="idPost == ''">
          <q-btn class="q-ma-md" color="primary" label="Crear" @click="createPost()" />
        </div>
        <div v-else>
          <q-btn class="q-ma-md" color="primary" label="Modificar" @click="updatePost()" />
        </div>

 
      <q-card style="min-width: 350px">
        <q-card-section class="bg-primary text-white" style="min-height: 350px">

        <q-input outlined v-model="titleT" placeholder="Titulo Traducido" label="Outlined" />
        <div class="q-pa-md q-gutter-sm">
            <q-editor
              v-model="contentT"
              flat
              content-class="bg-black"
              toolbar-text-color="white"
              toolbar-toggle-color="black-8"
              toolbar-bg="green"
              :toolbar="[
                ['bold', 'italic', 'underline'],
                [{
                  label: $q.lang.editor.formatting,
                  icon: $q.iconSet.editor.formatting,
                  list: 'no-icons',
                  options: ['p', 'h3', 'h4', 'h5', 'h6', 'code']
                }]
              ]"/>
          </div>

        </q-card-section>

        <q-separator />

        <q-card-actions align="right" >
        <q-select style="min-width: 200px"
          label="To translate language"
          v-model="transCode"
          :options="languagesT"
          />
        </q-card-actions>
      </q-card>



    </div>
  </q-page>
</template>

<script>
import { date } from 'quasar';
export default {
  name: 'PostForm',
  data(){
    return{
      idPost: '',
      titleO: '',
      contentO: '',
      titleT: '',
      contentT: '',
      langOrig: '',
      langTrans: '',
      origCode:null,
      transCode:null,
      languages: [],
      languagesT: []
    }
  },
  async created(){
    await this.getIdiomas()
    this.idPost = this.$route.query.idPost
    if(this.idPost != null|| this.idPost != null){
      await this.getPost();
    }
  },
  methods:{
    async getPost(){
      var request = {
        params:{
          'idPost': this.idPost
        }
      }
      let postGetted = await this.$axios("http://localhost:8080/getPost",request);
      let post = postGetted.data
      this.titleT = post.title
      this.contentT = post.content
      
      let translated = this.languages.find((language)=> {
        if(language.value == post.langOriginal){
          return language
        }
      }) 
      let toTranslate = this.languages.find((language)=> {
        if(language.value == post.langTranslate){
          return language
        }
      }) 

      this.origCode = translated
      this.transCode = toTranslate
      console.log(translated)
      console.log(toTranslate)
      request = {
        params:{
          MethodName: "translate",
          params: {
            source: this.transCode.value,
            target: this.origCode.value,
            text: this.contentT
          }
        }
      }
      let traduccionContent = await this.$axios.post(
        "http://server247.cfgs.esliceu.net/bloggeri18n/blogger.php",request);
      var traduccionDataContent = await traduccionContent.data;
      request = {
        params:{
          MethodName: "translate",
          params: {
            source: this.transCode.value,
            target: this.origCode.value,
            text: this.titleT
          }
        }
      }

      let traduccionTitle = await this.$axios.post(
        "http://server247.cfgs.esliceu.net/bloggeri18n/blogger.php",request);
      var traduccionDataTitle = await traduccionTitle.data;


    this.contentO = traduccionDataContent 
    this.titleO = traduccionDataTitle


    },
    async getIdiomas() {  
      const request = {
        params:{
          MethodName: "languages",
          params: ''
        }
      }

      let idiomas = await this.$axios.post("http://server247.cfgs.esliceu.net/bloggeri18n/blogger.php",request);

      let idiomasData = await idiomas;
      this.languages = idiomasData.data.map((idioma)=>{
        let objeto = {
          'value':idioma.code,
          'label':idioma.name
        }
        return objeto
      });
      this.languagesT = this.languages
    },
    async translate(origenTexto){
      var request ={};
      if(origenTexto == 'content'){
        request = {
          params:{
            MethodName: "translate",
            params: {
              source: this.origCode.value,
              target: this.transCode.value,
              text: this.contentO
            }
          }
        }
      }else{
        request = {
          params:{
            MethodName: "translate",
            params: {
              source: this.origCode.value,
              target: this.transCode.value,
              text: this.titleO
            }
          }
        }
      }
      let traduccion = await this.$axios.post(
        "http://server247.cfgs.esliceu.net/bloggeri18n/blogger.php",request);
      var traduccionData = await traduccion.data;
      
      if(origenTexto == 'content'){
        this.contentT = traduccionData;
      }else{
        this.titleT = traduccionData;
      }
    },
    createPost: function(){

      const request = {
        params:{
          'title':this.titleT,
          'content':this.contentT,
          'langOriginal':this.origCode.value,
          'langTranslate':this.transCode.value,
        },
        headers:{
          "Content-Type": "application/json"
        }
      }
      
        this.$axios.post("http://localhost:8080/createPost",request);
    },
    updatePost: function(){

      const request = {
        params:{
          'idPost':this.idPost,
          'title':this.titleT,
          'content':this.contentT,
          'langOriginal':this.origCode.value,
          'langTranslate':this.transCode.value,
        },
        headers:{
          "Content-Type": "application/json"
        }
      }
      
        this.$axios.put("http://localhost:8080/updatePost",request);
    }


  }
  
}
</script>
