package view;

import model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
public class Home
{
    protected static String myUserName;
    protected static int myID;
    protected static boolean businessAccount;
    static Scanner scanner = new Scanner(System.in);
    public static void home () throws SQLException
    {
        int x;
        System.out.println("***************************** home page *****************************");
        System.out.println("you logged in as " + myUserName + "!");
        while (true)
        {
            System.out.println("1.Create new post");
            System.out.println("2.See other's posts");
            System.out.println("3.See your own posts");
            System.out.println("4.Search a user");
            System.out.println("5.Your chats");
            System.out.println("6.Your groups");
            System.out.println("7.Your followers");
            System.out.println("8.Your followings");
            System.out.println("9.Suggested users");
            if(businessAccount)
            {
                System.out.println("10.Profile views");
                System.out.println("11.Logout");
            }
            else
            {
                System.out.println("10.Logout");
            }
            x = scanner.nextInt();
            if (x==1)
            {
                System.out.println("Enter the content...");
                scanner.nextLine();
                String postContent=scanner.nextLine();
                User.createNewPost(myID,postContent,businessAccount);
                System.out.println("You created a new post successfuly!");
            }
            if (x==2)
            {
                ArrayList<Integer> postIDs=User.getFollowingsPostIDByID(myID);
                postIDs.addAll(User.getSuggestedPostIDs(myID));
                int i;
                for(i=postIDs.size()-1;i>=0;)
                {
                    System.out.println(User.getUserNameByID(User.getUserIDByContentID(postIDs.get(i)))+" posted : "+User.getContentByContentID(postIDs.get(i)));
                    if(User.getBoolADByID(User.getUserIDByContentID(postIDs.get(i))))
                    {
                        System.out.println("AD Post!");
                    }
                    if(User.getBoolADByID(User.getUserIDByContentID(postIDs.get(i))))
                    {
                        User.postview(postIDs.get(i));
                    }
                    boolean postLiked = User.liked(myID,postIDs.get(i));
                    if(postLiked)
                    {
                        System.out.println("1.Unlike");
                    }
                    else
                    {
                        System.out.println("1.Like");
                    }
                    System.out.println("2.Comment");
                    System.out.println("3.See likes");
                    System.out.println("4.See comments");
                    System.out.println("5.Next post");
                    System.out.println("6.Previos post");
                    System.out.println("7.Exit");
                    int y=scanner.nextInt();
                    int j=i;
                    if(y==1 && postLiked)
                    {
                        User.unlike(myID,postIDs.get(i));
                    }
                    if(y==1 && !postLiked)
                    {
                        String Date = " ";
                        if(User.getBoolADByID(User.getUserIDByContentID(postIDs.get(i))))
                        {
                            Date = java.time.LocalDate.now().toString();
                        }
                        else
                        {
                            Date = "0";
                        }
                        User.like(myID,postIDs.get(i),Date);
                    }
                    if(y==2)
                    {
                        System.out.print("Enter your comment : ");
                        scanner.nextLine();
                        String comment=scanner.nextLine();
                        User.comment(myID,postIDs.get(i),comment);
                        System.out.println("You commented successfully!");
                    }
                    if(y==3)
                    {
                        ArrayList<String> userNames = User.userNamesOfLikes(postIDs.get(i));
                        if(userNames.size()==0)
                        {
                            System.out.println("No likes!");
                        }
                        else
                        {
                            System.out.println("Likes :");
                            int k;
                            for (k = 0; k < userNames.size(); k++)
                            {
                                System.out.println(userNames.get(k));
                            }
                        }
                    }
                    if(y==4)
                    {
                        ArrayList<Integer> commentsIDs = User.getCommentsIDOfPost(postIDs.get(i));
                        ArrayList<Integer> UserIDs = User.getUserIDsForComments(postIDs.get(i));
                        if(commentsIDs.size()==0)
                        {
                            System.out.println("No comments!");
                        }
                        else
                        {
                            int k;
                            for (k = commentsIDs.size() - 1; k >= 0; )
                            {
                                System.out.println(User.getUserNameByID(UserIDs.get(k))+" commented : "+User.getContentByContentID(commentsIDs.get(k)));
                                boolean commentLiked = User.liked(myID, commentsIDs.get(k));
                                if (commentLiked)
                                {
                                    System.out.println("1.Unlike");
                                }
                                else
                                {
                                    System.out.println("1.Like");
                                }
                                System.out.println("2.Reply");
                                System.out.println("3.See likes");
                                System.out.println("4.View replies");
                                System.out.println("5.Next comment");
                                System.out.println("6.Previous comment");
                                System.out.println("7.Exit");
                                int z = scanner.nextInt();
                                if (z == 1 && commentLiked)
                                {
                                    User.unlike(myID, commentsIDs.get(k));
                                }
                                if (z == 1 && !commentLiked)
                                {
                                    User.like(myID, commentsIDs.get(k),"0");
                                }
                                if (z == 2)
                                {
                                    System.out.print("Enter your reply : ");
                                    scanner.nextLine();
                                    String reply = scanner.nextLine();
                                    User.reply(myID, commentsIDs.get(k), reply);
                                    System.out.println("You replied successfully!");
                                }
                                if (z == 3)
                                {
                                    ArrayList<String> userNames = User.userNamesOfLikes(commentsIDs.get(k));
                                    if (userNames.size() == 0)
                                    {
                                        System.out.println("No likes!");
                                    }
                                    else
                                    {
                                        System.out.println("Likes :");
                                        int h;
                                        for (h = 0; h < userNames.size(); h++)
                                        {
                                            System.out.println(userNames.get(h));
                                        }
                                    }
                                }
                                if (z == 4)
                                {
                                    ArrayList<String> replies = User.getRepliesOfComment(commentsIDs.get(k));
                                    int h;
                                    if(replies.size()==0)
                                    {
                                        System.out.println("No replies!");
                                    }
                                    else
                                    {
                                        for (h = replies.size() - 1; h >= 0; h--)
                                        {
                                            System.out.println(replies.get(h));
                                        }
                                    }
                                }
                                if (z==5 && k==0)
                                {
                                    System.out.println("There is no more comments!");
                                }
                                if (z==5 && k!=0)
                                {
                                    k--;
                                }
                                if(z==6 && k==commentsIDs.size()-1)
                                {
                                    System.out.println("There is no previous comments!");
                                }
                                if (z==6 && k!=commentsIDs.size()-1)
                                {
                                    k++;
                                }
                                if(z==7)
                                {
                                    break;
                                }
                            }
                        }
                    }
                    if(y==5 && j!=0)
                    {
                        i--;
                    }
                    if(y==5 && j==0)
                    {
                        System.out.println("Ther is no more posts!");
                    }
                    if(y==6 && j!= postIDs.size()-1)
                    {
                        i++;
                    }
                    if(y==6 && j== postIDs.size()-1)
                    {
                        System.out.println("There is no previous post!");
                    }
                    if(y==7)
                    {
                        break;
                    }
                }
            }
            if (x==3)
            {
                ArrayList<Integer> postsIDs=User.getPostsIDByUserID(myID);
                int i;
                for(i= postsIDs.size()-1;i>=0;)
                {
                    System.out.println("You posted : "+User.getContentByContentID(postsIDs.get(i)));
                    System.out.println("1.Next post");
                    System.out.println("2.Previous post");
                    System.out.println("3.Remove the post");
                    if(businessAccount)
                    {
                        System.out.println("4.See stats");
                        System.out.println("5.Exit");
                    }
                    else
                    {
                        System.out.println("4.Exit");
                    }
                    int y=scanner.nextInt();
                    int j=i;
                    if(y==1 && j!= 0)
                    {
                        i--;
                    }
                    if(y==1 && j==0)
                    {
                        System.out.println("Ther is no more posts!");
                    }
                    if(y==2 && j!= postsIDs.size()-1)
                    {
                        i++;
                    }
                    if(y==2 && j== postsIDs.size()-1)
                    {
                        System.out.println("Ther is no previous post!");
                    }
                    if(y==3)
                    {
                        User.removePostByID(postsIDs.get(i));
                        postsIDs=User.getPostsIDByUserID(myID);
                        System.out.println("You removed the post successfully!");
                        i= postsIDs.size()-1;
                    }
                    if(y==4 && businessAccount)
                    {
                        System.out.println("1.Views");
                        System.out.println("2.Likes");
                        System.out.println("3.Exit");
                        int z = scanner.nextInt();
                        if(z==1)
                        {
                            ArrayList<String> views = User.getPostViews(postsIDs.get(i));
                            int k;
                            for(k= views.size()-1;k>=0;k--)
                            {
                                System.out.println(views.get(k));
                            }
                        }
                        if(z==2)
                        {
                            ArrayList<String> likes = User.getLikesStats(postsIDs.get(i));
                            int k;
                            if(likes.size()==0)
                            {
                                System.out.println("No likes!");
                            }
                            else
                            {
                                for (k = likes.size()-1 ;k>=0; k--)
                                {
                                    System.out.println(likes.get(k));
                                }
                            }
                        }
                    }
                    if(y==4 && !businessAccount)
                    {
                        break;
                    }
                    if(y==5 && businessAccount)
                    {
                        break;
                    }
                }
                if(postsIDs.size()==0)
                {
                    System.out.println("No posts!");
                }
            }
            if (x==4)
            {
                System.out.println("Enter the user name...");
                String searchedUserName = scanner.next();
                if (User.getIDByUserName(searchedUserName) == -1)
                {
                    System.out.println("No result!");
                }
                else
                {
                    int searchedUserID = User.getIDByUserName(searchedUserName);
                    boolean ADAccount = User.getBoolADByID(searchedUserID);
                    if(ADAccount)
                    {
                        User.profileView(searchedUserID,java.time.LocalDate.now().toString());
                    }
                    System.out.println("1.See posts");
                    boolean followCheck=false;
                    ArrayList<Integer> followingsIdInsearch=User.getFollowingsIDByID(myID);
                    int i;
                    for(i=0;i< followingsIdInsearch.size();i++)
                    {
                        if(followingsIdInsearch.get(i).equals(searchedUserID))
                        {
                            followCheck=true;
                            break;
                        }
                    }
                    if(!followCheck)
                    {
                        System.out.println("2.Follow");
                    }
                    else
                    {
                        System.out.println("2.Unfollow");
                    }
                    int y = scanner.nextInt();
                    if (y == 1)
                    {
                        ArrayList<Integer> searchedUserPostsIDs=User.getPostsIDByUserID(searchedUserID);
                        for(i=searchedUserPostsIDs.size()-1;i>=0;)
                        {
                            System.out.println(searchedUserName+" posted : "+User.getContentByContentID(searchedUserPostsIDs.get(i)));
                            boolean liked = User.liked(myID,searchedUserPostsIDs.get(i));
                            if (ADAccount)
                            {
                                User.postview(searchedUserPostsIDs.get(i));
                            }
                            if(liked)
                            {
                                System.out.println("1.Unlike");
                            }
                            else
                            {
                                System.out.println("1.Like");
                            }
                            System.out.println("2.Comment");
                            System.out.println("3.See likes");
                            System.out.println("4.See comments");
                            System.out.println("5.Next post");
                            System.out.println("6.Previous Post");
                            System.out.println("7.Exit");
                            int z=scanner.nextInt();
                            int j=i;
                            if(z==1 && liked)
                            {
                                User.unlike(myID,searchedUserPostsIDs.get(i));
                            }
                            if(z==1 && !liked)
                            {
                                String Date=" ";
                                if(User.getBoolADByID(searchedUserID))
                                {
                                    Date = java.time.LocalDate.now().toString();
                                }
                                else
                                {
                                    Date = "0";
                                }
                                User.like(myID,searchedUserPostsIDs.get(i),Date);
                            }
                            if(z==3)
                            {
                                ArrayList<String> userNames=User.userNamesOfLikes(searchedUserPostsIDs.get(i));
                                if(userNames.size()==0)
                                {
                                    System.out.println("No likes!");
                                }
                                else
                                {
                                    System.out.println("Likes :");
                                    int k;
                                    for(k=0;k< userNames.size();k++)
                                    {
                                        System.out.println(userNames.get(k));
                                    }
                                }
                            }
                            if(z==5 && j!=0)
                            {
                                i--;
                            }
                            if(z==5 && j==0)
                            {
                                System.out.println("Ther is no more posts!");
                            }
                            if(z==6 && i!=searchedUserPostsIDs.size()-1)
                            {
                                i++;
                            }
                            if(z==6 && i==searchedUserPostsIDs.size()-1)
                            {
                                System.out.println("There is no previous post!");
                            }
                            if(z==7)
                            {
                                break;
                            }
                        }
                        if(searchedUserPostsIDs.size()==0)
                        {
                            System.out.println("No posts!");
                        }
                    }
                    if (y == 2 && !followCheck)
                    {
                        User.follow(myID, searchedUserID);
                        System.out.println("You followed " + searchedUserName + " successfully!");
                    }
                    if(y == 2 && followCheck)
                    {
                        User.unfollow(myID,searchedUserID);
                        System.out.println("You unfollowed "+searchedUserName +" successfully!");
                    }
                }
            }
            if (x==7)
            {
                ArrayList<String> followersUserName=User.getFollowersUserNameByID(myID);
                int i;
                for(i=0;i< followersUserName.size();i++)
                {
                    System.out.println(followersUserName.get(i));
                }
            }
            if (x==8)
            {
                ArrayList<String> followingsID=User.getFollowingsUserNameByID(myID);
                int i;
                for(i=0;i< followingsID.size();i++)
                {
                    System.out.println(followingsID.get(i));
                }
            }
            if (x==9)
            {
                ArrayList<Integer> suggestedUsersIDs = User.getSuggestedUserIDsByUserID(myID);
                int i;
                if(suggestedUsersIDs.size()==0)
                {
                    System.out.println("No suggested user!");
                }
                else
                {
                    System.out.println("Suggested users :");
                    for (i = 0; i < suggestedUsersIDs.size(); i++)
                    {
                        System.out.println(User.getUserNameByID(suggestedUsersIDs.get(i)));
                    }
                }
            }
            if (x==10 && businessAccount)
            {
                ArrayList<String> views = User.getProfilViewsByID(myID);
                int i;
                for(i=views.size()-1;i>=0;i--)
                {
                    System.out.println(views.get(i));
                }
            }
            if (x==10 && !businessAccount)
            {
                myUserName = null;
                Manager.HomeBool = false;
                Manager.LoginBool = true;
                break;
            }
            if(x==11 && businessAccount)
            {
                myUserName = null;
                Manager.HomeBool = false;
                Manager.LoginBool = true;
                break;
            }
        }
    }
}
