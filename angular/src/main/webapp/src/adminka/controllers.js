var adminkaControllers = angular.module('adminkaControllers',
    ['adminkaDirectives', 'service', 'angularFileUpload']);

adminkaControllers.controller('AdminkaMainCtrl', ['$scope', 'Config',
    function ($scope, Config) {
        $scope.items = Config.getAdminkaMenuItems();
    }]);

adminkaControllers.controller('AdminkaImagesCtrl',
    ['$scope', 'Image', '$upload', 'globalMessageService',
        function ($scope, Image, $upload, globalMessageService) {
            $scope.images = Image.query();

            $scope.addImage = function () {
                $scope.images.push({edit: true});
                console.log($scope.images.$resolved);
            }
            $scope.edit = function (image) {
                image.edit = true;
                image.old = angular.extend({}, image, {});
            }
            $scope.cancel = function (image) {
                angular.extend(image, image.old);
                delete image.old;
                image.edit = false;
            }
            $scope.save = function (image) {
                $scope.saving = true;
                $upload.upload({
                    url: '/api/image',
                    method: 'POST',
                    data: image
                }).progress(function (evt) {
                        console.log('percent: ' + parseInt(100.0 * evt.loaded / evt.total));
                    }).success(function (data) {
                        console.log('s');
                        console.log(data);
                        angular.extend(image, data);
                        image.edit = false;
                        delete image.old;
                        globalMessageService.addSuccess("Image saved successfully.")
                        $scope.saving = false;
                    }).error(function () {
                        globalMessageService.addDanger("Error during saving.")
                        $scope.saving = false;
                    });

            }
            $scope.remove = function (image) {
                $scope.deleting = true;
                $scope.images.splice($scope.images.indexOf(image), 1);
                console.log($scope.images.length);
                Image.delete({id: image.id}, {},
                    function(data, headers){
                        $scope.deleting = false;
                    },
                    function(response){
                        $scope.deleting = false;
                        //TODO
                        console.log('ERROR');
                        console.log(response);
                    });
            }
            $scope.fileSelect = function (image, file) {
                image.file = file[0];
            }
        }]);

adminkaControllers.controller('AdminkaUsersCtrl', ['$scope',
    function ($scope) {

    }])