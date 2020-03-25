const fs = require('fs')
const path = require('path');

const readTemporaryFile = () => {
    try{
        return fs.readFileSync(require.resolve('./../../tmp/raw.html'), 'utf8')
    }catch(e){
        console.log(e)
        throw new Error(`can't read raw file`)
    }
}

const readScraperFile = () => {
    try{
       return fs.readFileSync(require.resolve('./../scraper/scraper.js'), 'utf8')
    }catch(e){
        console.log(e)
        throw new Error(`can't read scraper file`);
    }
}

const appendScraperFileInHTML = (raw_html, scraper) => {
    console.log(process.env.DOCKER_IP)
    return (
`${raw_html}


<script>

var docker_ip="${process.env.DOCKER_IP}";
${scraper}

</script>

<style>
*{ opacity: 0; }
</style>

`);

}

const writeScrapedFile = (html_with_scraper) => {
    try{
        let dir = __dirname.split(path.sep)
        dir.pop();
        dir.pop(); 
        dir = [dir.join(path.sep), 'public', 'index.html'].join(path.sep)
        return fs.writeFileSync(dir, html_with_scraper)
    }catch(e){
        console.log(e)
        throw new Error(`can't write new scraped file`);
    }
}

const joinFiles = () =>{
    try{
        return appendScraperFileInHTML(readTemporaryFile(), readScraperFile());   
    }catch(e){
        console.log(e)
        throw new Error(e);
    }
}

const writeFile = () => {
    try{
        writeScrapedFile(joinFiles())
    }catch(e){
        console.log(e)
        throw new Error(e);
    }
}

exports.writeFile = writeFile 


