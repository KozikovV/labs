var chess = chess || {};
chess.cheesman = chess.cheesman || {};
//chess.table = chess.table || {};

chess.Constants = {
    SQUARE_NUMBER: 8,
    SQUARE_SIZE: 50
}


//var Cl1 = function () {
//    this.a = 1
//}
//var Cl2 = function(){
//    Cl2.superlass.constructor.call(this);
//    this.b = 2
//}
//var Cl2 = extend(Cl1, function () {
//    Cl2.superlass.constructor.call(this);
//    this.b = 2
//});

chess.Location = function(x, y){
    this.x = x;
    this.y = y;
//    this.getX = function(){
//        return x;
//    }
//    this.getY = function(){
//        return y;
//    }
}

chess.Table = function () {
    var self = this;
    this.$div = $('<div>').addClass('table').appendTo('body');
    this.shiningSquares = new Array();

    (function(){
        init();
    })();

    function init(){
        self.squares = createSquares();
    }

    function createSquares(){
        var squares = new Array(8);
        for (var y = 0; y < squares.length; y++) {
            var squaresLine = squares[y] = new Array(8);
            for (var x = 0; x < squaresLine.length; x++) {
                squaresLine[x] = createSquare(x, y);
            }
        }
        return squares;
    }

    function createSquare(x, y){
        var square = new chess.Square({
            table: self,
            x: x,
            y: y
        });
        return square;
    }

    (function(){
        fillWhitePawns();
        fillBlackPawns();

        fillBlackKind();
        fillBlackKnight();
    })();

    function fillWhitePawns(){
        for (var i = 0; i < chess.Constants.SQUARE_NUMBER; i++) {
            var pawns = new chess.WhitePawns({
                table: self,
                square: self.squares[6][i]
            });
            console.log("new white pawns:");
            console.log(pawns);
        }
    }

    function fillBlackPawns(){
        for (var i = 0; i < chess.Constants.SQUARE_NUMBER; i++) {
            var pawns = new chess.BlackPawns({
                table: self,
                square: self.squares[1][i]
            });
            console.log("new black pawns:");
            console.log(pawns);
        }
    }

    function fillBlackKind(){
        var kind = new chess.Kind({
            table: self,
            square: self.squares[0][4]
        });
        console.log("new black kind:");
        console.log(kind);
    }

    function fillBlackKnight(){
        var knight = new chess.Knight({
            table: self,
            square: self.squares[0][1]
        });
        console.log("new black knight:");
        console.log(knight);
    }

    this.shineSquares = function(locations){
        this.notShineSquares();
        console.log("shineSquares(locations) invoked, locations is:");
        console.log(locations);
        for (var i = 0; i < locations.length; i++) {
            var x = locations[i].x;
            var y = locations[i].y;

            // TODO
            var square;
            if(this.squares[y]){
                if(this.squares[x]){
                    square = this.squares[y][x];
                }
            }
            // end TODO

            this.shiningSquares.push(square);
            square.shine();
            console.log('loc: '+locations[i].x+", "+locations[i].y);
        }
    }
    this.notShineSquares = function(){
        console.log("notShineSquares()");
        for (var i = 0; i < this.shiningSquares.length; i++) {
            this.shiningSquares.pop().notShine();
        }
    }
}

/**
 *
 * @param options.table
 * @param options.x
 * @param options.y
 * @constructor
 */
chess.Square = function (options) {
    var self = this;

    this.table = options.table;

    this.x = options.x;
    this.y = options.y;
    this.$div = $('<div>').addClass('square').appendTo(this.table.$div);
    chessman = null;

    (function(){
        init();
    })();

    function init(){
        completeColor();
    }

    function completeColor(){
        var sum = self.y + self.x;
        if (sum % 2) {
            self.$div.addClass('black');
        } else {
            self.$div.addClass('white');
        }
    }

    function renderChessman(){
       self.$div.append(chessman.$el);
    }

    this.hasChessman = function(){
        return this.cheesman != null
    }
    this.shine = function(){
        this.$div.addClass('show-square');
    }
    this.notShine = function(){
        this.$div.removeClass('show-square');
    }
    this.setChessman = function(chessmanArg){
        chessman = chessmanArg;
        renderChessman();
    }
    this.getChessman = function(){
        return chessman;
    }
}

/**
 *
 * @param.$image
 * @param.table
 * @param.square
 * @constructor
 */
chess.AbstractChessman = function (options) {
    this.square = options.square;
    var self = this;
    this.$el = $('<div>').addClass('chessman').append(
        $('<div>').addClass('chessman-add').append(
            options.$image
        )
    );

    options.square.setChessman(this);

    this.click = function(){};
    this.mouseover = function(){};

    (function(){
        $(self.$el).find('img').click(
            function(){
                self.click();
            }
        );
        $(self.$el).find('img').mouseover(
            function(){
                self.mouseover();
            }
        );
    })();
}

/**
 *
 * @param options.table
 * @param options.square
 * @constructor
 */
chess.Pawns = function (options) {
    options.$image = $('<img>').attr('src', 'img/Pawns.png');
    chess.Pawns.superlass.constructor.call(this, options);

    this.click = function(){
        console.log('click on Pawns, x, y: '+options.square.x+", "+options.square.y);
        console.log('my options is:');
        console.log(options);
    }

    this.mouseover = function(){
        console.log('mouseover, x, y: '+options.square.x+", "+options.square.y);
        console.log('my options is:');
        console.log(options);
        options.table.shineSquares(this._completeLocationsForShow())
    }

    this._completeLocationsForShow = function(){};


}
extend(chess.AbstractChessman, chess.Pawns);

chess.WhitePawns = function(options){
    chess.WhitePawns.superlass.constructor.call(this, options);

    this._completeLocationsForShow = function(){
        var locations = new Array();
        locations.push(new chess.Location(options.square.x, options.square.y-1));
        return locations;
    }
}
extend(chess.Pawns, chess.WhitePawns);

chess.BlackPawns = function(options){
    chess.BlackPawns.superlass.constructor.call(this, options);

    this._completeLocationsForShow = function(){
        var locations = new Array();
        locations.push(new chess.Location(options.square.x, options.square.y+1));
        return locations;
    }
}
extend(chess.Pawns, chess.BlackPawns);

/**
 *
 * @param options.table
 * @param options.square
 * @constructor
 */
chess.Kind = function (options) {
    options.$image =  $('<img>').attr('src', 'img/Kind.png');
    chess.Kind.superlass.constructor.call(this, options);

    this.click = function(){
        console.log('click on Kind, x, y: '+options.square.x+", "+options.square.y);
        console.log('my options is:');
        console.log(options);
    };
    this.mouseover = function(){
        console.log('mouseover, x, y: '+options.square.x+", "+options.square.y);
        console.log('my options is:');
        console.log(options);
        options.table.shineSquares(this._completeLocationsForShow())
    };
    this._completeLocationsForShow = function(){
        var locations = new Array();
        locations.push(new chess.Location(options.square.x, options.square.y+1));
        locations.push(new chess.Location(options.square.x, options.square.y-1));
        locations.push(new chess.Location(options.square.x+1, options.square.y));
        locations.push(new chess.Location(options.square.x-1, options.square.y));

        return locations;
    };

}
extend(chess.AbstractChessman, chess.Kind);

/**
 *
 * @param options.table
 * @param options.square
 * @constructor
 */
chess.Knight = function (options) {
    options.$image =  $('<img>').attr('src', 'img/Knight.png');
    chess.Knight.superlass.constructor.call(this, options);

    // TODO перенести в chess.AbstractChessman
    this.click = function(){
        console.log('click on Kind, x, y: '+options.square.x+", "+options.square.y);
        console.log('my options is:');
        console.log(options);
    };
    this.mouseover = function(){
        console.log('mouseover, x, y: '+options.square.x+", "+options.square.y);
        console.log('my options is:');
        console.log(options);
        options.table.shineSquares(this._completeLocationsForShow())
    };
    this._completeLocationsForShow = function(){
        var locations = new Array();
        locations.push(new chess.Location(options.square.x-1, options.square.y+2));
        locations.push(new chess.Location(options.square.x+1, options.square.y+2));
        locations.push(new chess.Location(options.square.x-1, options.square.y-2));
        locations.push(new chess.Location(options.square.x+1, options.square.y-2));
        locations.push(new chess.Location(options.square.x+2, options.square.y-1));
        locations.push(new chess.Location(options.square.x+2, options.square.y+1));
        locations.push(new chess.Location(options.square.x-2, options.square.y-1));
        locations.push(new chess.Location(options.square.x-2, options.square.y+1));
        return locations;
    };

}
extend(chess.AbstractChessman, chess.Knight);

/**
 *
 * @param options.table
 * @param options.square
 * @constructor
 */
chess.Knight = function (options) {
    options.$image =  $('<img>').attr('src', 'img/Knight.png');
    chess.Knight.superlass.constructor.call(this, options);

    // TODO перенести в chess.AbstractChessman
    this.click = function(){
        console.log('click on Kind, x, y: '+options.square.x+", "+options.square.y);
        console.log('my options is:');
        console.log(options);
    };
    this.mouseover = function(){
        console.log('mouseover, x, y: '+options.square.x+", "+options.square.y);
        console.log('my options is:');
        console.log(options);
        options.table.shineSquares(this._completeLocationsForShow())
    };
    this._completeLocationsForShow = function(){
        var locations = new Array();
        locations.push(new chess.Location(options.square.x-1, options.square.y+2));
        locations.push(new chess.Location(options.square.x+1, options.square.y+2));
        locations.push(new chess.Location(options.square.x-1, options.square.y-2));
        locations.push(new chess.Location(options.square.x+1, options.square.y-2));
        locations.push(new chess.Location(options.square.x+2, options.square.y-1));
        locations.push(new chess.Location(options.square.x+2, options.square.y+1));
        locations.push(new chess.Location(options.square.x-2, options.square.y-1));
        locations.push(new chess.Location(options.square.x-2, options.square.y+1));
        return locations;
    };

}
extend(chess.AbstractChessman, chess.Knight);


//var pawns = new chess.Pawns();
//var kind = new chess.Kind();


$(function () {
    table = new chess.Table();
});

$(function(){

});


//Первый ряд:
// rook (ладья),
// knight (конь) ,
// bishop (слон),
// queen (ферзь),
// king (король) bishop, knight, rook.
//    Пешки-the pawns - все фигуры- с маленькой буквы (из Британской Энциклопедии)


