$(function(){

    class Search {

        constructor() {
            this.load()
        }

        listWrap = $("#article-list")

        async load() {
            this.#setLoading();
            const data = await this.#getData()
            const html = data.map(this.#renderItem).join(' ')
            this.listWrap.empty().html(html);
        }

        async #getData() {
            await new Promise(resolve => setTimeout(resolve, 2000));
            return await $.get('/data/records.json')
        }

        #setLoading() {
            this.listWrap.empty().html(`<div class="d-flex justify-content-center">
              <div class="spinner-border" role="status">
                <span class="visually-hidden">Loading...</span>
              </div>
            </div>`)
        }

        #renderItem(item) {
            const { no, source = 'rct' } = item
            const target = btoa(JSON.stringify({ no, source }));
            const link = ctxPath + `/article/${target}`
            return `
             <article class="post">
              <div class="card">
                <div class="card-body">
                  <div class="post-header">
                    <div class="post-category gap-1 text-line">
                      <span class="badge bg-primary rounded-pill">${item.no}</span>
                      <span class="badge bg-yellow rounded-pill">随机对照试验</span>
                      <span class="badge bg-ash text-white rounded-pill">麝香保心丸</span>
                      <span class="badge bg-ash text-white rounded-pill">急性冠脉综合征</span>
                      <span class="badge bg-ash text-white rounded-pill">心率变异</span>
                      <span class="badge bg-ash text-white rounded-pill">I24.901</span>
                    </div>
                    <h4 class="post-title mt-1 mb-0"><a target="_blank" href="${link}">${item.title_zh}</a></h4>
                    <p class="small">${item.title_en}</p>
                  </div>
                  <div class="post-content fs-sm">
                    <p class="mb-0">麝香保心丸;急性冠脉综合征;心率变异</p>
                    <p class="mb-0">急性冠脉综合征 (急性冠脉综合征)</p>
                    <p class="mb-0">心血管康复医学杂志</p>
                  </div>
                </div>
                <div class="card-footer">
                  <ul class="post-meta d-flex mb-0">
                    <li class="post-date"><i class="uil uil-calendar-alt"></i><span>2005/14/3/244~246</span></li>
                    <li class="post-author"><a href="#"><i class="uil uil-user"></i><span>吴剑萍;张俏红;戚本玲</span></a></li>
                  </ul>
                </div>
              </div>
            </article>
            `
        }

    }

    new Search();

})