// const puppeteer = require('puppeteer')
const axios = require('axios')
const jsdom = require("jsdom");
const { JSDOM } = jsdom;

const compiler = async (url) => {
    console.log('send to api');
    // try{
    // const response = await axios.get(`http://localhost:${process.env.SOURCE_PORT}/index.html`)

    // const dom = new JSDOM(response.data, {runScripts: "dangerously"});
    // console.log(response.data);
    // }catch(e){
    // console.log(e);
    // }
    // const browser = await puppeteer.launch({ executablePath: '/usr/bin/chromium-browser', args: ['--no-sandbox', '--disable-dev-shm-usage']})
    // const page = await browser.newPage();
    // try{
    //     await page.goto(`http://localhost:${process.env.SOURCE_PORT}/index.html`)
    // }catch(e){
    //     console.log(e)
    // }
    // console.log('await...');

    // page.waitFor(15000)
    // console.log('finished');
    // await browser.close();
} 

exports.compiler = compiler