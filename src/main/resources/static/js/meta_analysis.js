/**
 * meta分析
 */
class MetaAnalysis {
    constructor(selector, params) {
        this.$root = $(selector);
        this.params = params;
        this.init();
    }

    init(){
        this.bind();
        this.load()
    }

    bind(){
        const self = this;
        this.$root
            .on('click', '[data-download]', function(){
                alert('download')
            })
            .on('submit', '[data-edit-form]', function(event){
                const data = $(this).serialize()
                console.log(data)
                self.load();
                event.preventDefault();
            })
    }

    async load(){
        this.setLoading()
        await new Promise((resolve) =>  setTimeout(resolve, 2000))
        const data = await $.get(`/data/meta_${this.params.type}.json`)
        const html = this.render(data)
        this.$root.empty().html(html);
    }

    setLoading() {
        const html = `
            <div class="card">
                 <div class="card-body">
                    <div class="text-center">
                      <div class="spinner-border" role="status">
                        <span class="visually-hidden">Loading...</span>
                      </div>
                      <p class="my-4 fs-sm">正在生成图片，可能需要一点时间</p>
                    </div>
                </div>
            </div>
        `
        this.$root.empty().html(html)
    }

    render(data){
        return `
            <div class="card">
              <div class="card-header">
                <strong>${data.type_desc}</strong>
                <small>${data.type}</small>
              </div>
              <figure class="card-img-top overlay overlay-1 hover-scale">
                  <img src="${data.image}" style="width: auto" alt="Forest plot of fixed model"/>
              </figure>
              <div class="card-footer">
                  <form data-edit-form class="d-flex flex-row gap-2">
                    <div class="input-group input-group-sm">
                        <span class="input-group-text">长度</span>
                        <input class="form-control d-inline form-control-sm" type="text" name="width" placeholder="长度" value="${data.height}">
                    </div>
                    <div class="input-group input-group-sm">
                        <span class="input-group-text">宽度</span>
                        <input class="form-control d-inline form-control-sm" type="text" name="height" placeholder="宽度" value="${data.width}">
                    </div>
                    <button class="btn btn-sm btn-outline-primary">重做</button>
                  </form>
              </div>
          </div>
        `
    }
}