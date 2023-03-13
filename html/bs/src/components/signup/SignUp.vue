<template>
    <div>
        <!-- 导航栏 -->
        <Header/>
        <div></div>
        <Footer/>
    </div>
</template>

<script>
import Footer from '.././Footer'
import Header from '.././Header.vue'
export default {
    data(){
        return{

        }
    },
    components:{
        Footer:Footer,
        Header:Header,
    },
}
</script>

<style>

</style>