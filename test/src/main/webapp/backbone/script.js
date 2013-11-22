var app = {};



(function ($) {

    var Bean = Backbone.Model.extend({
        defaults: function () {
            return{
                'name': "asd"
            }
        }
    });

    var Beans = Backbone.Collection.extend({
        model: Bean,
        localStorage: new Backbone.LocalStorage("beans-backbone")
    });

    var BeanView = Backbone.View.extend({
        template: _.template($('#bean-template').html()),
        initialize: function(){
            this.listenTo(this.model, 'change', this.render);
        },
        events: {
            'dblclick .bean-label': 'edit',
            'blur .bean-edit': 'editEnd',
            'click .bean-remove': 'remove'
        },
        render: function(){
            this.$el.html(this.template(this.model.toJSON()));
            this.$input = this.$('.bean-edit');
            return this;
        },
        edit: function(event){
            this.$el.addClass('bean-editing');
            this.$input.focus();
        },
        editEnd: function(event){
            this.model.set('name', event.target.value)
            this.$el.removeClass('bean-editing');
        },
        remove: function(){

        }
    });

    var AppView = Backbone.View.extend({
//        'template': _.template($('#template').html()),

        el: $('div#main'),
        $input: $('input#input'),
        $listViews: $('section#content'),

        initialize: function () {
            this.render();
            this.listenTo(app.beans, 'add', this.addBean);
        },
        events: {
            'click input#add': 'inputData',
            'keypress input#input': 'inputData'
        },

        render: function () {

        },

        inputData: function (event) {
            if(event.keyCode != $.ui.keyCode.ENTER &&
                event.target.type != 'button') return;
            app.beans.create({'name': this.$input.val()});
        },

        addBean: function(bean){
            var beanView = new BeanView({model: bean});
            this.$listViews.append(beanView.render().$el);
        }
    });





//    var bean = new Bean();

//    var beans = new Beans();
//    beans.on('add', function (bean) {
//        console.log(bean.get('name'));
//    });
//    beans.add(bean);



    app.beans = new Beans();
    app.beans.create({'name':"name001"});
    app.appView = new AppView();

    var v = new BeanView({model: new Bean()});



    console.log('end');
})(jQuery);